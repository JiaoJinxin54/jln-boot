package top.jiaojinxin.configuration;

import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import top.jiaojinxin.system.log.logback.filter.LogbackFilter;

/**
 * logback自动装配
 *
 * @author JiaoJinxin
 */
public class LogbackConfiguration {

    /**
     * logback自动装配
     */
    public LogbackConfiguration() {
    }

    /**
     * logback过滤器
     *
     * @return top.jiaojinxin.system.log.logback.filter.LogbackFilter
     */
    @Bean
    @ConditionalOnClass(ILoggingEvent.class)
    public LogbackFilter logbackFilter() {
        return new LogbackFilter();
    }
}
