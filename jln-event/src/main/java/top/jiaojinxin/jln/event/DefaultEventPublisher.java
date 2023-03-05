package top.jiaojinxin.jln.event;

import com.lmax.disruptor.SleepingWaitStrategy;
import top.jiaojinxin.jln.properties.JlnEventProperties;
import top.jiaojinxin.jln.util.EventManager;

/**
 * 默认事件处理器发布者
 *
 * @author JiaoJinxin
 */
public class DefaultEventPublisher extends AbstractEventPublisher {

    /**
     * 构造方法
     *
     * @param eventProperties 事件相关配置
     * @author JiaoJinxin
     */
    public DefaultEventPublisher(JlnEventProperties eventProperties) {
        super(
                eventProperties.getRingBufferSize(),
                eventProperties.getThreadNamePrefix(),
                eventProperties.getProducerType(),
                new SleepingWaitStrategy()
        );
    }

    @Override
    protected void doPublish(Event event) {
        EventManager.getEventHandlerRepository()
                .find(event.getClass())
                .parallelStream()
                .forEach(eventHandler -> eventHandler.handle(event));
    }
}