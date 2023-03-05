package top.jiaojinxin.jln.event;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.InsufficientCapacityException;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 事件发布者抽象实现
 *
 * @author JiaoJinxin
 */
public abstract class AbstractEventPublisher implements EventPublisher, ApplicationRunner,
        ApplicationListener<ContextClosedEvent>, EventHandler<AbstractEventPublisher.DisruptorEvent> {

    private static final String DASHED = "-";

    private final Disruptor<DisruptorEvent> disruptor;

    private final RingBuffer<DisruptorEvent> ringBuffer;

    private boolean disruptorEnd = true;

    /**
     * 构造方法
     *
     * @param ringBufferSize   循环队列长度
     * @param threadNamePrefix 线程名称前缀
     * @param producerType     生产者类型
     * @param waitStrategy     等待策略
     * @author JiaoJinxin
     */
    protected AbstractEventPublisher(int ringBufferSize, String threadNamePrefix, ProducerType producerType, WaitStrategy waitStrategy) {
        this.disruptor = new Disruptor<>(DisruptorEvent::new, ringBufferSize, new NameThreadFactory(threadNamePrefix), producerType, waitStrategy);
        this.disruptor.handleEventsWith(this);
        this.ringBuffer = this.disruptor.getRingBuffer();
    }

    @Override
    public void publish(Event event) {
        if (event == null || this.disruptorEnd) {
            return;
        }
        try {
            long next = this.ringBuffer.tryNext();
            DisruptorEvent disruptorEvent = this.ringBuffer.get(next);
            disruptorEvent.setEvent(event);
            this.ringBuffer.publish(next);
        } catch (InsufficientCapacityException e) {
            // 若等待队列满了之后，使用当前线程执行
            doPublish(event);
        }
    }

    @Override
    public void onEvent(DisruptorEvent event, long sequence, boolean endOfBatch) {
        doPublish(event.getEvent());
    }

    @Override
    public void run(ApplicationArguments args) {
        this.disruptor.start();
        this.disruptorEnd = false;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        this.disruptorEnd = true;
        this.disruptor.shutdown();
    }

    /**
     * 执行事件发布
     *
     * @param event 事件
     * @author JiaoJinxin
     */
    protected abstract void doPublish(Event event);

    /**
     * disruptor事件
     *
     * @author JiaoJinxin
     */
    public static class DisruptorEvent {

        /**
         * 事件对象
         */
        private Event event;

        /**
         * 获取{@link DisruptorEvent#event}
         *
         * @return top.jiaojinxin.jln.event.Event
         * @author JiaoJinxin
         */
        public Event getEvent() {
            return event;
        }

        /**
         * 设置{@link DisruptorEvent#event}
         *
         * @param event {@link DisruptorEvent#event}
         * @author JiaoJinxin
         */
        public void setEvent(Event event) {
            this.event = event;
        }
    }

    private static class NameThreadFactory implements ThreadFactory {

        private final AtomicInteger index = new AtomicInteger(0);

        private final String threadNamePrefix;

        private NameThreadFactory(String threadNamePrefix) {
            this.threadNamePrefix = threadNamePrefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            String formatTemplate = threadNamePrefix.endsWith(DASHED) ? "%s%s" : "%s-%s";
            return new Thread(r, String.format(formatTemplate, threadNamePrefix, index.getAndIncrement()));
        }
    }
}
