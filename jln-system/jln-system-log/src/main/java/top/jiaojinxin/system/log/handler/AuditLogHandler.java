package top.jiaojinxin.system.log.handler;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.extra.spring.SpringUtil;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import top.jiaojinxin.util.I18nManager;
import top.jiaojinxin.log.audit.annotation.Log;
import top.jiaojinxin.log.audit.aspect.LogHandler;
import top.jiaojinxin.system.log.model.AuditLogDetails;
import top.jiaojinxin.util.LogUtil;
import top.jiaojinxin.util.CurrUserUtil;
import top.jiaojinxin.util.HttpServletUtil;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static cn.hutool.core.text.CharSequenceUtil.EMPTY;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.isNull;
import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 * 审计日志处理
 *
 * @author JiaoJinxin
 */
@RequiredArgsConstructor
public class AuditLogHandler implements LogHandler {

    /**
     * 用于执行日志发布的虚拟线程池
     */
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newVirtualThreadPerTaskExecutor();

    /**
     * 日志处理器
     */
    private final List<LogDetailsHandler> handlers;

    @Override
    public boolean canHandle(Log log) {
        return LogHandler.super.canHandle(log);
    }

    @Override
    public void preHandle(Log log, Object[] args) {
        AuditLogDetails logDetails = new AuditLogDetails();
        // 开始时间
        logDetails.setRequestStart(LocalDateTime.now());
        // 操作
        logDetails.setOperation(I18nManager.getMsg(log.operation()));
        // 类型
        logDetails.setOperation(I18nManager.getMsg(log.type()));
        // 设置日志详情到TTL中
        LogUtil.setLogDetails(logDetails);
    }

    @Override
    public void postHandle(Log log, Object result, Throwable throwable) {
        // 从TTL中获取日志详情
        AuditLogDetails logDetails = LogUtil.getLogDetails();
        // 获取请求
        HttpServletRequest request = HttpServletUtil.getRequest();
        // 获取userAgent
        String userAgentStr = JakartaServletUtil.getHeader(request, USER_AGENT, UTF_8);
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
        // 应用
        logDetails.setAppName(SpringUtil.getApplicationName());
        // 操作人
        logDetails.setOperator(CurrUserUtil.getUserCode());
        // 请求uri
        logDetails.setUrl(request.getRequestURI());
        // 请求方式
        logDetails.setMethod(request.getMethod());
        // 客户端ip
        logDetails.setClientIp(JakartaServletUtil.getClientIP(request));
        // 设备类型
        logDetails.setDeviceType(userAgent.getOperatingSystem().getDeviceType().getName());
        // 操作系统
        logDetails.setOperatingSystem(userAgent.getOperatingSystem().getName());
        // 浏览器
        logDetails.setBrowser(userAgent.getBrowser().getGroup().getName());
        // 浏览器版本
        Version browserVersion = userAgent.getBrowser().getVersion(userAgentStr);
        logDetails.setBrowserVersion(isNull(browserVersion) ? EMPTY : browserVersion.getVersion());
        // 结束时间
        logDetails.setRequestEnd(LocalDateTime.now());
        // 请求耗时（毫秒）
        logDetails.setRequestUse(LocalDateTimeUtil.between(logDetails.getRequestStart(), logDetails.getRequestEnd(), ChronoUnit.MILLIS));
    }

    @Override
    public void publish() {
        AuditLogDetails logDetails = LogUtil.getLogDetails();
        handlers.forEach(handler -> EXECUTOR_SERVICE.submit(() -> handler.handle(logDetails)));
        LogUtil.clearLogDetails();
    }
}
