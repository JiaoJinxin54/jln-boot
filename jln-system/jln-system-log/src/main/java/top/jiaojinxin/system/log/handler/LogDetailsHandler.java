package top.jiaojinxin.system.log.handler;

import top.jiaojinxin.system.log.model.AuditLogDetails;

/**
 * 日志详情处理器
 *
 * @author JiaoJinxin
 */
public interface LogDetailsHandler {

    /**
     * 处理审计日志详情
     *
     * @param auditLogDetails 审计日志详情
     */
    void handle(AuditLogDetails auditLogDetails);
}
