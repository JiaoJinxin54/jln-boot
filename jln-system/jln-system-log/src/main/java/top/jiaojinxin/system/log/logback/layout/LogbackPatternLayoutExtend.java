package top.jiaojinxin.system.log.logback.layout;

import ch.qos.logback.classic.PatternLayout;
import top.jiaojinxin.system.log.constants.LogConstants;
import top.jiaojinxin.system.log.logback.pattern.TraceIdConverter;

/**
 * 继承自{@link PatternLayout}，用于扩展logback日志中pattern内容的转换实现
 *
 * @author JiaoJinxin
 */
public class LogbackPatternLayoutExtend extends PatternLayout {

    static {
        DEFAULT_CONVERTER_MAP.put(LogConstants.TRACE_ID, TraceIdConverter.class.getName());
    }
}
