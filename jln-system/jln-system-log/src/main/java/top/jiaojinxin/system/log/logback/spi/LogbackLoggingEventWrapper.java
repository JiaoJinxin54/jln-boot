package top.jiaojinxin.system.log.logback.spi;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.LoggerContextVO;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Marker;
import org.slf4j.event.KeyValuePair;
import top.jiaojinxin.util.LogUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static top.jiaojinxin.system.log.constants.LogConstants.TRACE_ID;

/**
 * {@link ILoggingEvent}包装类，用于扩展内容的存取
 *
 * @author JiaoJinxin
 */
public class LogbackLoggingEventWrapper implements ILoggingEvent {

    private final ILoggingEvent loggingEvent;

    private final Map<String, String> extend = new ConcurrentHashMap<>();

    /**
     * {@link ILoggingEvent}包装类
     *
     * @param loggingEvent {@link ILoggingEvent}
     */
    private LogbackLoggingEventWrapper(ILoggingEvent loggingEvent) {
        this.loggingEvent = loggingEvent;
    }

    /**
     * 创建{@link LogbackLoggingEventWrapper}
     *
     * @param loggingEvent {@link ILoggingEvent}
     * @return top.jiaojinxin.system.log.spi.logback.log.LogbackLoggingEventWrapper
     */
    public static LogbackLoggingEventWrapper of(ILoggingEvent loggingEvent) {
        LogbackLoggingEventWrapper event = new LogbackLoggingEventWrapper(loggingEvent);
        // 日志链路追踪标识
        event.putExtend(TRACE_ID, LogUtil.getTraceId());
        return event;
    }

    /**
     * 获取扩展内容值
     *
     * @param key 键
     * @return java.lang.String
     */
    public String getExtendValue(String key) {
        return this.extend.getOrDefault(key, StrUtil.EMPTY);
    }

    /**
     * 扩展内容键值对
     *
     * @param key   键
     * @param value 值
     */
    public void putExtend(String key, String value) {
        if (StrUtil.isBlank(key) || StrUtil.isBlank(value)) {
            return;
        }
        this.extend.put(key, value);
    }

    @Override
    public String getThreadName() {
        return this.loggingEvent.getThreadName();
    }

    @Override
    public Level getLevel() {
        return this.loggingEvent.getLevel();
    }

    @Override
    public String getMessage() {
        return this.loggingEvent.getMessage();
    }

    @Override
    public Object[] getArgumentArray() {
        return this.loggingEvent.getArgumentArray();
    }

    @Override
    public String getFormattedMessage() {
        return this.loggingEvent.getFormattedMessage();
    }

    @Override
    public String getLoggerName() {
        return this.loggingEvent.getLoggerName();
    }

    @Override
    public LoggerContextVO getLoggerContextVO() {
        return this.loggingEvent.getLoggerContextVO();
    }

    @Override
    public IThrowableProxy getThrowableProxy() {
        return this.loggingEvent.getThrowableProxy();
    }

    @Override
    public StackTraceElement[] getCallerData() {
        return this.loggingEvent.getCallerData();
    }

    @Override
    public boolean hasCallerData() {
        return this.loggingEvent.hasCallerData();
    }

    @Override
    public List<Marker> getMarkerList() {
        return this.loggingEvent.getMarkerList();
    }

    @Override
    public Map<String, String> getMDCPropertyMap() {
        return this.loggingEvent.getMDCPropertyMap();
    }

    @Override
    @Deprecated
    public Map<String, String> getMdc() {
        return this.loggingEvent.getMdc();
    }

    @Override
    public long getTimeStamp() {
        return this.loggingEvent.getTimeStamp();
    }

    @Override
    public int getNanoseconds() {
        return this.loggingEvent.getNanoseconds();
    }

    @Override
    public long getSequenceNumber() {
        return this.loggingEvent.getSequenceNumber();
    }

    @Override
    public List<KeyValuePair> getKeyValuePairs() {
        return this.loggingEvent.getKeyValuePairs();
    }

    @Override
    public void prepareForDeferredProcessing() {
        this.loggingEvent.prepareForDeferredProcessing();
    }
}
