package top.jiaojinxin.jln.util;

import top.jiaojinxin.jln.event.Event;
import top.jiaojinxin.jln.event.EventHandler;

import static top.jiaojinxin.jln.util.EventManager.getEventHandlerRepository;
import static top.jiaojinxin.jln.util.EventManager.getEventHandlers;
import static top.jiaojinxin.jln.util.EventManager.getEventPublisher;

/**
 * <p>事件管理工具类</p>
 * <p><b>注意：由于相互之间的依赖关系，顾set方法调用有顺序，被需要的先调用</b></p>
 *
 * @author JiaoJinxin
 */
public class EventUtil {
    private EventUtil() {
    }

    /**
     * 静态增加事件处理器
     *
     * @param eventHandler 事件处理器
     * @author JiaoJinxin
     */
    public static void addEventHandler(EventHandler<Event> eventHandler) {
        getEventHandlers().add(eventHandler);
        getEventHandlerRepository().register(eventHandler);
    }

    /**
     * 静态移除事件处理器
     *
     * @param eventHandler 事件处理器
     * @author JiaoJinxin
     */
    public static void removeEventHandler(EventHandler<Event> eventHandler) {
        getEventHandlers().remove(eventHandler);
        getEventHandlerRepository().remove(eventHandler);
    }

    /**
     * 静态发布事件
     *
     * @param event 事件
     * @author JiaoJinxin
     */
    public static void publish(Event event) {
        getEventPublisher().publish(event);
    }
}
