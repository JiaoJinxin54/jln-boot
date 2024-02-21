package top.jiaojinxin.log.audit.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import top.jiaojinxin.log.audit.annotation.Log;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 日志切面
 *
 * @author JiaoJinxin
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class AuditLogAspect {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newVirtualThreadPerTaskExecutor();

    private final List<LogHandler> logHandlers;

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
        // 获取可以处理的日志记录器
        List<LogHandler> logHandlerList = logHandlers.stream().filter(logHandler -> logHandler.canHandle(log)).toList();
        // 前置处理
        logHandlerList.forEach(logHandler -> logHandler.preHandle(log, point.getArgs()));
        Object result = null;
        Throwable throwable = null;
        try {
            result = point.proceed();
            return result;
        } catch (Throwable e) {
            throwable = e;
            throw e;
        } finally {
            final Object r = result;
            final Throwable t = throwable;
            logHandlerList.forEach(logHandler -> {
                logHandler.postHandle(log, r, t);
                EXECUTOR_SERVICE.submit(logHandler::publish);
            });
        }
    }
}
