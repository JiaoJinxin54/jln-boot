package top.jiaojinxin.jln.config.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.jiaojinxin.jln.log.event.AuditLogEventHandler;
import top.jiaojinxin.jln.model.DefaultAuditLogEvent;
import top.jiaojinxin.jln.util.LogUtil;

/**
 * 审计日志打印事件处理实现
 *
 * @author JiaoJinxin
 */
public class PrintAuditLogEventHandler implements AuditLogEventHandler<DefaultAuditLogEvent> {

    private static final Logger log = LoggerFactory.getLogger(PrintAuditLogEventHandler.class);

    @Override
    public void doHandle(DefaultAuditLogEvent event) {
        LogUtil.info(log, event.getEventString());
    }

    @Override
    public Class<DefaultAuditLogEvent> eventClass() {
        return DefaultAuditLogEvent.class;
    }
}
