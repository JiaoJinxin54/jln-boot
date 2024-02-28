package top.jiaojinxin.system.log.logback.appender;

import ch.qos.logback.classic.AsyncAppender;
import ch.qos.logback.classic.spi.ILoggingEvent;
import top.jiaojinxin.system.log.logback.spi.LogbackLoggingEventWrapper;

/**
 * logback日志记录器（异步）
 *
 * @author JiaoJinxin
 */
public class LogbackAsyncAppender extends AsyncAppender {

    @Override
    protected void append(ILoggingEvent eventObject) {
        super.append(LogbackLoggingEventWrapper.of(eventObject));
    }
}
