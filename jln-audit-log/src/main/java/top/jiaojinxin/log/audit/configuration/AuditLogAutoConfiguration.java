package top.jiaojinxin.log.audit.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import top.jiaojinxin.log.audit.aspect.AuditLogAspect;
import top.jiaojinxin.log.audit.handler.LogAnnotationHandler;
import top.jiaojinxin.log.audit.handler.SimpleLogAnnotationHandler;

/**
 * 审计日志自动装配
 *
 * @author JiaoJinxin
 */
public class AuditLogAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(LogAnnotationHandler.class)
    public LogAnnotationHandler<?> logAnnotationHandler() {
        return new SimpleLogAnnotationHandler();
    }

    @Bean
    public AuditLogAspect auditLogAspect(LogAnnotationHandler<?> logAnnotationHandler) {
        return new AuditLogAspect(logAnnotationHandler);
    }
}
