package top.jiaojinxin.jln.log;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import top.jiaojinxin.jln.log.annotation.AuditLog;
import top.jiaojinxin.jln.log.event.AuditLogEvent;

/**
 * 审计日志处理器
 *
 * @author JiaoJinxin
 */
public interface AuditLogHandler<T extends AuditLogEvent> {

    /**
     * 初始化日志
     *
     * @return T
     * @author JiaoJinxin
     */
    T initAuditLogEvent();

    /**
     * 请求执行之前执行
     *
     * @param request       请求
     * @param handler       请求处理器
     * @param auditLog      审计日志注解
     * @param auditLogEvent 日志
     * @author JiaoJinxin
     */
    default void before(HttpServletRequest request, HandlerMethod handler, AuditLog auditLog, T auditLogEvent) {
    }

    /**
     * 请求执行之后执行
     *
     * @param request       请求
     * @param response      响应
     * @param handler       请求处理器
     * @param ex            异常
     * @param auditLogEvent 日志
     * @author JiaoJinxin
     */
    default void after(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, Exception ex, T auditLogEvent) {
    }
}
