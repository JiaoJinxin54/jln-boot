package top.jiaojinxin.log.audit.aspect;

import lombok.NonNull;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import top.jiaojinxin.log.audit.annotation.Log;
import top.jiaojinxin.log.audit.model.LogDetails;

/**
 * 日志处理程序
 *
 * @param <T> 日志详情泛型
 * @author JiaoJinxin
 */
public interface LogAnnotationHandler<T extends LogDetails> extends ApplicationListener<PayloadApplicationEvent<T>> {

    /**
     * 初始化日志详情
     *
     * @return T
     */
    T init();

    /**
     * 前置处理
     *
     * @param logDetails 日志详情
     * @param log        日志注解
     * @param args       目标方法入参
     */
    void preHandle(@NonNull T logDetails, Log log, Object[] args);

    /**
     * 后置处理
     *
     * @param logDetails 日志详情
     * @param result     结果
     * @param throwable  异常
     */
    void postHandle(@NonNull T logDetails, Object result, Throwable throwable);

    /**
     * 发布日志
     *
     * @param logDetails 日志详情
     */
    void publish(@NonNull T logDetails);

    @Override
    default void onApplicationEvent(PayloadApplicationEvent<T> event) {
        publish(event.getPayload());
    }
}
