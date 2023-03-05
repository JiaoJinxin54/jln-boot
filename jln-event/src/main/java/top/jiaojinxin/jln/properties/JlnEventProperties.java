package top.jiaojinxin.jln.properties;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serial;
import java.io.Serializable;

/**
 * 事件处理配置
 *
 * @author JiaoJinxin
 */
@ConfigurationProperties(prefix = "jln.event")
public class JlnEventProperties implements Serializable {

    @Serial
    private static final long serialVersionUID = 1785438259534047437L;

    /**
     * {@link RingBuffer}的长度，默认：128
     */
    private int ringBufferSize = 128;

    /**
     * 线程名称前缀，默认：event-thread
     */
    private String threadNamePrefix = "event-thread";

    /**
     * 生产者类型，默认：{@link ProducerType#SINGLE}
     */
    private ProducerType producerType = ProducerType.SINGLE;

    /**
     * 获取{@link JlnEventProperties#ringBufferSize}
     *
     * @return int
     * @author JiaoJinxin
     */
    public int getRingBufferSize() {
        return ringBufferSize;
    }

    /**
     * 设置{@link JlnEventProperties#ringBufferSize}
     *
     * @param ringBufferSize {@link JlnEventProperties#ringBufferSize}
     * @author JiaoJinxin
     */
    public void setRingBufferSize(int ringBufferSize) {
        this.ringBufferSize = ringBufferSize;
    }

    /**
     * 获取{@link JlnEventProperties#threadNamePrefix}
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }

    /**
     * 设置{@link JlnEventProperties#threadNamePrefix}
     *
     * @param threadNamePrefix {@link JlnEventProperties#threadNamePrefix}
     * @author JiaoJinxin
     */
    public void setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }

    /**
     * 获取{@link JlnEventProperties#producerType}
     *
     * @return com.lmax.disruptor.dsl.ProducerType
     * @author JiaoJinxin
     */
    public ProducerType getProducerType() {
        return producerType;
    }

    /**
     * 设置{@link JlnEventProperties#producerType}
     *
     * @param producerType {@link JlnEventProperties#producerType}
     * @author JiaoJinxin
     */
    public void setProducerType(ProducerType producerType) {
        this.producerType = producerType;
    }
}
