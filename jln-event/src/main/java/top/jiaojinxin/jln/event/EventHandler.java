package top.jiaojinxin.jln.event;

/**
 * 事件处理器
 *
 * @author JiaoJinxin
 */
public interface EventHandler<E extends Event> {

    /**
     * 处理事件
     *
     * @param event 事件
     * @author JiaoJinxin
     */
    void doHandle(E event);

    /**
     * 事件Class
     *
     * @return java.lang.Class
     * @author JiaoJinxin
     */
    Class<E> eventClass();

    /**
     * 处理事件
     *
     * @param event 事件
     * @author JiaoJinxin
     */
    default void handle(Event event) {
        if (eventClass().isAssignableFrom(event.getClass())) {
            doHandle((E) event);
        }
    }
}
