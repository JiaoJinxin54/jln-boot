package top.jiaojinxin.jln.util;

import top.jiaojinxin.jln.event.DefaultEventHandlerRepository;
import top.jiaojinxin.jln.event.DefaultEventPublisher;
import top.jiaojinxin.jln.event.Event;
import top.jiaojinxin.jln.event.EventHandler;
import top.jiaojinxin.jln.event.EventHandlerRepository;
import top.jiaojinxin.jln.event.EventPublisher;
import top.jiaojinxin.jln.properties.JlnEventProperties;

import java.util.Collections;
import java.util.List;

/**
 * <p>事件管理工具类</p>
 * <p><b>注意：由于相互之间的依赖关系，顾set方法调用有顺序，被需要的先调用</b></p>
 *
 * @author JiaoJinxin
 */
public class EventManager {
    private EventManager() {
    }

    /**
     * 事件处理配置
     */
    private static volatile JlnEventProperties eventProperties;

    /**
     * 获取{@link EventManager#eventProperties}
     *
     * @return top.jiaojinxin.jln.properties.JlnEventProperties
     * @author JiaoJinxin
     */
    public static JlnEventProperties getEventProperties() {
        if (EventManager.eventProperties == null) {
            synchronized (EventManager.class) {
                if (EventManager.eventProperties == null) {
                    setEventProperties(new JlnEventProperties());
                }
            }
        }
        return EventManager.eventProperties;
    }

    /**
     * 设置{@link EventManager#eventProperties}
     *
     * @param eventProperties {@link EventManager#eventProperties}
     * @author JiaoJinxin
     */
    public static void setEventProperties(JlnEventProperties eventProperties) {
        EventManager.eventProperties = eventProperties;
    }

    /**
     * 事件处理器
     */
    private static volatile List<EventHandler<? extends Event>> eventHandlers;

    /**
     * 获取{@link EventManager#eventHandlers}
     *
     * @return java.util.List
     * @author JiaoJinxin
     */
    public static List<EventHandler<? extends Event>> getEventHandlers() {
        if (EventManager.eventHandlers == null) {
            synchronized (EventManager.class) {
                if (EventManager.eventHandlers == null) {
                    setEventHandlers(Collections.emptyList());
                }
            }
        }
        return EventManager.eventHandlers;
    }

    /**
     * 设置{@link EventManager#eventHandlers}
     *
     * @param eventHandlers {@link EventManager#eventHandlers}
     * @author JiaoJinxin
     */
    public static void setEventHandlers(List<EventHandler<? extends Event>> eventHandlers) {
        EventManager.eventHandlers = eventHandlers;
        getEventHandlerRepository().registerAll(eventHandlers);
    }

    /**
     * 事件处理器存储库
     */
    private static volatile EventHandlerRepository eventHandlerRepository;

    /**
     * 获取{@link EventManager#eventHandlerRepository}
     *
     * @return top.jiaojinxin.jln.event.EventHandlerRepository
     * @author JiaoJinxin
     */
    public static EventHandlerRepository getEventHandlerRepository() {
        if (EventManager.eventHandlerRepository == null) {
            synchronized (EventManager.class) {
                if (EventManager.eventHandlerRepository == null) {
                    setEventHandlerRepository(new DefaultEventHandlerRepository());
                }
            }
        }
        return EventManager.eventHandlerRepository;
    }

    /**
     * 设置{@link EventManager#eventHandlerRepository}
     *
     * @param eventHandlerRepository {@link EventManager#eventHandlerRepository}
     * @author JiaoJinxin
     */
    public static void setEventHandlerRepository(EventHandlerRepository eventHandlerRepository) {
        EventManager.eventHandlerRepository = eventHandlerRepository;
        EventManager.eventHandlerRepository.registerAll(getEventHandlers());
    }

    /**
     * 事件发布者
     */
    private static volatile EventPublisher eventPublisher;

    /**
     * 获取{@link EventManager#eventPublisher}
     *
     * @return top.jiaojinxin.jln.event.EventPublisher
     * @author JiaoJinxin
     */
    public static EventPublisher getEventPublisher() {
        if (EventManager.eventPublisher == null) {
            synchronized (EventManager.class) {
                if (EventManager.eventPublisher == null) {
                    setEventPublisher(new DefaultEventPublisher(getEventProperties()));
                }
            }
        }
        return EventManager.eventPublisher;
    }

    /**
     * 设置{@link EventManager#eventPublisher}
     *
     * @param eventPublisher {@link EventManager#eventPublisher}
     * @author JiaoJinxin
     */
    public static void setEventPublisher(EventPublisher eventPublisher) {
        EventManager.eventPublisher = eventPublisher;
    }
}
