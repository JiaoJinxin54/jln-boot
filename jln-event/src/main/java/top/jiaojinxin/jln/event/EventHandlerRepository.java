package top.jiaojinxin.jln.event;

import java.util.Collection;
import java.util.List;

/**
 * 事件处理器存储库
 *
 * @author JiaoJinxin
 */
public interface EventHandlerRepository {

    /**
     * 事件处理器注册
     *
     * @param eventHandler 事件处理器
     * @author JiaoJinxin
     */
    void register(EventHandler<? extends Event> eventHandler);

    /**
     * 事件批量注册（直接覆盖，多用于存储库初始化）
     *
     * @param eventHandlers 事件集合
     * @author JiaoJinxin
     */
    void registerAll(Collection<EventHandler<? extends Event>> eventHandlers);

    /**
     * 事件处理器移除
     *
     * @param eventHandler 事件处理器
     * @author JiaoJinxin
     */
    void remove(EventHandler<? extends Event> eventHandler);

    /**
     * 查找事件处理器
     *
     * @param clazz 事件Class
     * @return java.util.List
     * @author JiaoJinxin
     */
    List<EventHandler<? extends Event>> find(Class<? extends Event> clazz);
}
