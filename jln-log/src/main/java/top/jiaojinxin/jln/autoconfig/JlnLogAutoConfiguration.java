package top.jiaojinxin.jln.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import top.jiaojinxin.jln.log.AuditLogHandler;
import top.jiaojinxin.jln.log.event.AuditLogEvent;

/**
 * 日志相关自动装配
 *
 * @author JiaoJinxin
 */
public class JlnLogAutoConfiguration {

    /**
     * 审计日志处理器不存在时，注册默认实现
     *
     * @return top.jiaojinxin.jln.log.AuditLogHandler
     * @author JiaoJinxin
     */
    @Bean
    @ConditionalOnMissingBean(AuditLogHandler.class)
    public AuditLogHandler<? extends AuditLogEvent> auditLogHandler() {
        return () -> new AuditLogEvent() {
        };
    }
}
