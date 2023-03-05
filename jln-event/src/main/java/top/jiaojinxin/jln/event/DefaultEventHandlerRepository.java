package top.jiaojinxin.jln.event;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 默认事件处理器存储库
 *
 * @author JiaoJinxin
 */
public class DefaultEventHandlerRepository implements EventHandlerRepository {

    private final Map<Class<? extends Event>, List<EventHandler<? extends Event>>> repository = new HashMap<>();

    @Override
    public void register(EventHandler<? extends Event> eventHandler) {
        this.repository.putIfAbsent(eventHandler.eventClass(), new ArrayList<>());
        List<EventHandler<? extends Event>> eventHandlers = this.repository.get(eventHandler.eventClass());
        if (eventHandlers.contains(eventHandler)) {
            return;
        }
        eventHandlers.add(eventHandler);
    }

    @Override
    public void registerAll(Collection<EventHandler<? extends Event>> eventHandlers) {
        if (CollectionUtils.isEmpty(eventHandlers)) {
            return;
        }
        this.repository.clear();
        this.repository.putAll(eventHandlers.stream().collect(Collectors.groupingBy(EventHandler::eventClass)));
    }

    @Override
    public void remove(EventHandler<? extends Event> eventHandler) {
        if (this.repository.containsKey(eventHandler.eventClass())) {
            this.repository.get(eventHandler.eventClass()).remove(eventHandler);
        }
    }

    @Override
    public List<EventHandler<? extends Event>> find(Class<? extends Event> clazz) {
        return this.repository.getOrDefault(clazz, Collections.emptyList());
    }
}