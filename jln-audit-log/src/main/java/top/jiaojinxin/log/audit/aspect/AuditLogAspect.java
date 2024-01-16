package top.jiaojinxin.log.audit.aspect;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import top.jiaojinxin.log.audit.annotation.Log;
import top.jiaojinxin.log.audit.model.LogDetails;

/**
 * 日志切面
 *
 * @param <T> 日志详情泛型
 * @author JiaoJinxin
 */
@Slf4j
@Aspect
public abstract class AuditLogAspect<T extends LogDetails> implements LogAnnotationHandler<T>, ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 日志切面
     */
    public AuditLogAspect() {
    }

    /**
     * 环绕通知
     *
     * @param point {@link ProceedingJoinPoint}
     * @param log   {@link Log}
     * @return java.lang.Object
     * @throws java.lang.Throwable 目标方法执行异常
     */
    @Around("@annotation(log)")
    public Object around(ProceedingJoinPoint point, Log log) throws Throwable {
        T logDetails = init();
        preHandle(logDetails, log, point.getArgs());
        Object result = null;
        Throwable throwable = null;
        try {
            result = point.proceed();
        } catch (Throwable e) {
            throwable = e;
            throw e;
        } finally {
            postHandle(logDetails, result, throwable);
            applicationEventPublisher.publishEvent(logDetails);
        }
        return result;
    }

    @Override
    public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
