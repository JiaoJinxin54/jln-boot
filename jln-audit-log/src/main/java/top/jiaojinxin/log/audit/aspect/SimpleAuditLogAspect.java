package top.jiaojinxin.log.audit.aspect;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import top.jiaojinxin.log.audit.annotation.Log;
import top.jiaojinxin.log.audit.model.LogDetails;

import java.io.Serial;

/**
 * 简单日志注解切面实现
 *
 * @author JiaoJinxin
 */
@Slf4j
public class SimpleAuditLogAspect extends AuditLogAspect<SimpleAuditLogAspect.DefLogDetails> {

    /**
     * 简单日志注解切面实现
     */
    public SimpleAuditLogAspect() {
    }

    @Override
    public DefLogDetails init() {
        return new DefLogDetails();
    }

    @Override
    public void preHandle(@NonNull DefLogDetails logDetails, Log log, Object[] args) {
        logDetails.setOperation(log.operation());
    }

    @Override
    public void postHandle(@NonNull DefLogDetails logDetails, Object result, Throwable throwable) {
    }

    @Override
    public void publish(@NonNull DefLogDetails logDetails) {
        log.info("{}", logDetails);
    }

    /**
     * 默认日志详情
     *
     * @author JiaoJinxin
     */
    @Getter
    @Setter
    @ToString
    public static class DefLogDetails implements LogDetails {
        @Serial
        private static final long serialVersionUID = 5490160100089945203L;

        /**
         * 默认日志详情
         */
        public DefLogDetails() {
        }

        /**
         * 操作
         */
        private String operation;
    }
}
