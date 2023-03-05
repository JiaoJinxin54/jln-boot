package top.jiaojinxin.jln.event;

/**
 * 事件发布者
 *
 * @author JiaoJinxin
 */
public interface EventPublisher {

    /**
     * 发布事件
     *
     * @param event 事件
     * @author JiaoJinxin
     */
    void publish(Event event);
}
