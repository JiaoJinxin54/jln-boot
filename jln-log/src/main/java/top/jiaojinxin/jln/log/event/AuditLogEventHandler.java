package top.jiaojinxin.jln.log.event;

import top.jiaojinxin.jln.event.EventHandler;

/**
 * 日志事件处理器
 *
 * @author JiaoJinxin
 */
public interface AuditLogEventHandler<T extends AuditLogEvent> extends EventHandler<T> {
}
