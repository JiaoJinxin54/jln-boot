package top.jiaojinxin.log.audit.aspect;

import top.jiaojinxin.log.audit.annotation.Log;

/**
 * 审计日志记录器
 *
 * @author JiaoJinxin
 */
public interface LogHandler {

    /**
     * 是否可处理
     *
     * @param log 日志注解
     * @return boolean
     */
    default boolean canHandle(Log log) {
        return true;
    }

    /**
     * 目标方法执行之前记录日志
     *
     * @param log  日志注解
     * @param args 目标方法入参
     */
    default void preHandle(Log log, Object[] args) {
    }

    /**
     * 目标方法执行之后记录日志
     *
     * @param log       日志注解
     * @param result    结果
     * @param throwable 异常
     */
    default void postHandle(Log log, Object result, Throwable throwable) {
    }

    /**
     * 发布日志
     */
    void publish();
}
