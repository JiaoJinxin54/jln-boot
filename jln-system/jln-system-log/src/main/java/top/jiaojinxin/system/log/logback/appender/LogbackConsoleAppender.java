package top.jiaojinxin.system.log.logback.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import top.jiaojinxin.system.log.logback.spi.LogbackLoggingEventWrapper;

/**
 * logback日志记录器（打印到控制台）
 *
 * @author JiaoJinxin
 */
public class LogbackConsoleAppender extends ConsoleAppender<ILoggingEvent> {

    @Override
    protected void append(ILoggingEvent eventObject) {
        super.append(LogbackLoggingEventWrapper.of(eventObject));
    }
}
