package top.jiaojinxin.log.audit.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import top.jiaojinxin.log.audit.aspect.AuditLogAspect;
import top.jiaojinxin.log.audit.aspect.SimpleAuditLogAspect;

/**
 * 审计日志自动装配
 *
 * @author JiaoJinxin
 */
public class AuditLogAutoConfiguration {

    /**
     * 审计日志自动装配
     */
    public AuditLogAutoConfiguration() {
    }

    /**
     * 审计日志切面
     *
     * @return top.jiaojinxin.log.audit.aspect.AuditLogAspect
     */
    @Bean
    @ConditionalOnMissingBean(AuditLogAspect.class)
    public AuditLogAspect<?> auditLogAspect() {
        return new SimpleAuditLogAspect();
    }
}
