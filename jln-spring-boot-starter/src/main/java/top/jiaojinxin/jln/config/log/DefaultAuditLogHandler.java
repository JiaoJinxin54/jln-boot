package top.jiaojinxin.jln.config.log;

import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import top.jiaojinxin.jln.autoconfig.JlnLogAutoConfiguration;
import top.jiaojinxin.jln.log.AuditLogHandler;
import top.jiaojinxin.jln.log.annotation.AuditLog;
import top.jiaojinxin.jln.model.DefaultAuditLogEvent;
import top.jiaojinxin.jln.util.JlnUtil;

/**
 * 默认的{@link AuditLogHandler}实现
 *
 * @author JiaoJinxin
 */
@ConditionalOnMissingBean(AuditLogHandler.class)
@AutoConfigureBefore(JlnLogAutoConfiguration.class)
public class DefaultAuditLogHandler implements AuditLogHandler<DefaultAuditLogEvent>, EnvironmentAware {

    private Environment environment;

    @Override
    public DefaultAuditLogEvent initAuditLogEvent() {
        DefaultAuditLogEvent auditLogEvent = new DefaultAuditLogEvent();
        auditLogEvent.putAppName(this.environment.getProperty("spring.application.name", "-"));
        auditLogEvent.putProfile(this.environment.getProperty("spring.profiles.active", "-"));
        return auditLogEvent;
    }

    @Override
    public void before(HttpServletRequest request, HandlerMethod handler, AuditLog auditLog, DefaultAuditLogEvent auditLogEvent) {
        // 请求url
        auditLogEvent.putUrl(request.getRequestURI());
        // 请求方式
        auditLogEvent.putMethod(request.getMethod());
        // 客户端IP
        auditLogEvent.putClientIp(JlnUtil.getClientIP(request));
        String userAgentStr = request.getHeader(HttpHeaders.USER_AGENT);
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
        // 设备类型
        auditLogEvent.putDeviceType(userAgent.getOperatingSystem().getDeviceType().getName());
        // 操作系统
        auditLogEvent.putOperatingSystem(userAgent.getOperatingSystem().getName());
        // 浏览器
        auditLogEvent.putBrowser(userAgent.getBrowser().getGroup().getName());
        Version browserVersion = userAgent.getBrowser().getVersion(userAgentStr);
        // 浏览器版本
        auditLogEvent.putBrowserVersion(browserVersion == null ? null : browserVersion.getVersion());
        // 操作描述
        auditLogEvent.putDescription(auditLog.describe());
        // 开始时间
        auditLogEvent.putStart();
    }

    @Override
    public void after(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, Exception ex, DefaultAuditLogEvent auditLogEvent) {
        // 结束时间
        auditLogEvent.putEnd();
        // 耗时
        auditLogEvent.putUseTime();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
