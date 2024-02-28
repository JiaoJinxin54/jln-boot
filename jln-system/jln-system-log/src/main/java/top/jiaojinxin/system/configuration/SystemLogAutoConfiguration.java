package top.jiaojinxin.system.configuration;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import top.jiaojinxin.system.log.handler.AuditLogHandler;
import top.jiaojinxin.system.log.handler.LogDetailsHandler;
import top.jiaojinxin.system.properties.LogProperties;
import top.jiaojinxin.log.audit.aspect.LogHandler;
import top.jiaojinxin.log.audit.configuration.AuditLogAutoConfiguration;

/**
 * 审计日志自动装配
 *
 * @author JiaoJinxin
 */
@Slf4j
@EnableConfigurationProperties(LogProperties.class)
@AutoConfigureBefore(AuditLogAutoConfiguration.class)
public class SystemLogAutoConfiguration {

    @Bean
    public LogDetailsHandler defaultLogDetailsHandler() {
        return auditLogDetails -> log.info("{}", JSON.toJSONString(auditLogDetails));
    }

    @Bean
    public LogHandler logHandler(ObjectProvider<LogDetailsHandler> provider) {
        return new AuditLogHandler(provider.orderedStream().toList());
    }
}
