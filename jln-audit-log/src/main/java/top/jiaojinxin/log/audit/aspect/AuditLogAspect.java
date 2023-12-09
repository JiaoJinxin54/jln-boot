package top.jiaojinxin.log.audit.aspect;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import top.jiaojinxin.log.audit.annotation.Log;
import top.jiaojinxin.log.audit.handler.LogAnnotationHandler;
import top.jiaojinxin.log.audit.model.LogDetails;

/**
 * 日志切面
 *
 * @author JiaoJinxin
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class AuditLogAspect implements ApplicationEventPublisherAware {

    private final LogAnnotationHandler logAnnotationHandler;

    private ApplicationEventPublisher applicationEventPublisher;

    @Around("@annotation(log)")
    public Object around(ProceedingJoinPoint point, Log log) throws Throwable {
        LogDetails logDetails = logAnnotationHandler.init();
        logAnnotationHandler.preHandle(logDetails, log, point.getArgs());
        Object result = null;
        Throwable throwable = null;
        try {
            result = point.proceed();
        } catch (Throwable e) {
            throwable = e;
            throw e;
        } finally {
            logAnnotationHandler.postHandle(logDetails, result, throwable);
            applicationEventPublisher.publishEvent(logDetails);
        }
        return result;
    }

    @Override
    public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
