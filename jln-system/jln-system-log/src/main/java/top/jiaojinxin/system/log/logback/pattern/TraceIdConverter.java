package top.jiaojinxin.system.log.logback.pattern;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import cn.hutool.core.util.StrUtil;
import top.jiaojinxin.system.log.constants.LogConstants;
import top.jiaojinxin.system.log.logback.spi.LogbackLoggingEventWrapper;

/**
 * traceId转换器
 *
 * @author JiaoJinxin
 */
public class TraceIdConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        String traceId = null;
        if (event instanceof LogbackLoggingEventWrapper logbackLoggingEventWrapper) {
            traceId = logbackLoggingEventWrapper.getExtendValue(LogConstants.TRACE_ID);
        }
        return StrUtil.isBlank(traceId) ? StrUtil.EMPTY : traceId;
    }
}
