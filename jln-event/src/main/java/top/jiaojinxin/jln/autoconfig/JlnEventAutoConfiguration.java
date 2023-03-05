package top.jiaojinxin.jln.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import top.jiaojinxin.jln.event.DefaultEventHandlerRepository;
import top.jiaojinxin.jln.event.DefaultEventPublisher;
import top.jiaojinxin.jln.event.EventHandlerRepository;
import top.jiaojinxin.jln.event.EventPublisher;
import top.jiaojinxin.jln.properties.JlnEventProperties;

/**
 * 事件相关自动装配
 *
 * @author JiaoJinxin
 */
@EnableConfigurationProperties(JlnEventProperties.class)
public class JlnEventAutoConfiguration {

    /**
     * 事件处理器存储库不存在时，注册默认实现
     *
     * @return top.jiaojinxin.jln.event.EventHandlerRepository
     * @author JiaoJinxin
     */
    @Bean
    @ConditionalOnMissingBean(EventHandlerRepository.class)
    public EventHandlerRepository eventHandlerRepository() {
        return new DefaultEventHandlerRepository();
    }

    /**
     * 事件发布者不存在时，注册默认实现
     *
     * @param eventProperties 事件处理配置
     * @return top.jiaojinxin.jln.event.EventPublisher
     * @author JiaoJinxin
     */
    @Bean
    @ConditionalOnMissingBean(EventPublisher.class)
    public EventPublisher eventPublisher(JlnEventProperties eventProperties) {
        return new DefaultEventPublisher(eventProperties);
    }
}
