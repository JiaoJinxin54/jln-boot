package top.jiaojinxin.jln.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import top.jiaojinxin.jln.event.Event;
import top.jiaojinxin.jln.event.EventHandler;
import top.jiaojinxin.jln.event.EventHandlerRepository;
import top.jiaojinxin.jln.event.EventPublisher;
import top.jiaojinxin.jln.properties.JlnEventProperties;
import top.jiaojinxin.jln.util.EventManager;

import java.util.List;

/**
 * 事件相关自动注册
 *
 * @author JiaoJinxin
 */
@EnableConfigurationProperties(JlnEventProperties.class)
public class JlnEventAutoRegistration {

    /**
     * 将事件处理器存储库注册到静态工具类中，以便在非Spring Bean场景中静态调用
     *
     * @param eventHandlerRepository 事件处理器存储库
     * @author JiaoJinxin
     */
    @Autowired
    public void setEventHandlerRepository(EventHandlerRepository eventHandlerRepository) {
        EventManager.setEventHandlerRepository(eventHandlerRepository);
    }

    /**
     * 将事件发布者注册到静态工具类中，以便在非Spring Bean场景中静态调用
     *
     * @param eventPublisher 事件发布者
     * @author JiaoJinxin
     */
    @Autowired
    public void setEventPublisher(EventPublisher eventPublisher) {
        EventManager.setEventPublisher(eventPublisher);
    }

    /**
     * 将事件处理配置注册到静态工具类中，以便在非Spring Bean场景中静态调用
     *
     * @param eventProperties 事件处理配置
     * @author JiaoJinxin
     */
    @Autowired
    public void setJlnEventProperties(JlnEventProperties eventProperties) {
        EventManager.setEventProperties(eventProperties);
    }

    /**
     * 将事件处理器注册到静态工具类中，以便在非Spring Bean场景中静态调用
     *
     * @param eventHandlers 事件处理器集合
     * @author JiaoJinxin
     */
    @Autowired(required = false)
    public void setEventHandlers(List<EventHandler<? extends Event>> eventHandlers) {
        EventManager.setEventHandlers(eventHandlers);
    }
}
