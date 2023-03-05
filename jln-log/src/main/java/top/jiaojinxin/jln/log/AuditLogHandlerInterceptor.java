package top.jiaojinxin.jln.log;

import com.alibaba.ttl.TransmittableThreadLocal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.jiaojinxin.jln.log.annotation.AuditLog;
import top.jiaojinxin.jln.log.event.AuditLogEvent;
import top.jiaojinxin.jln.util.EventUtil;

/**
 * 审计日志拦截器
 *
 * @author JiaoJinxin
 */
public class AuditLogHandlerInterceptor<T extends AuditLogEvent> implements AsyncHandlerInterceptor {

    private final ThreadLocal<T> TL = new TransmittableThreadLocal<>();

    private final AuditLogHandler<T> auditLogHandler;

    /**
     * 构造方法
     *
     * @param auditLogHandler 审计日志处理器
     * @author JiaoJinxin
     */
    public AuditLogHandlerInterceptor(AuditLogHandler<T> auditLogHandler) {
        this.auditLogHandler = auditLogHandler;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod handlerMethod && handlerMethod.hasMethodAnnotation(AuditLog.class)) {
            TL.set(this.auditLogHandler.initAuditLogEvent());
            this.auditLogHandler.before(request, handlerMethod, handlerMethod.getMethodAnnotation(AuditLog.class), TL.get());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (handler instanceof HandlerMethod handlerMethod && handlerMethod.hasMethodAnnotation(AuditLog.class)) {
            this.auditLogHandler.after(request, response, handlerMethod, ex, TL.get());
            EventUtil.publish(TL.get());
            TL.remove();
        }
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) {
    }
}
