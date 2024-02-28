package top.jiaojinxin.system.util;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import top.jiaojinxin.system.log.model.AuditLogDetails;

/**
 * 日志工具类
 *
 * @author JiaoJinxin
 */
public class LogUtil {

    /**
     * traceId TTL
     */
    private static final TransmittableThreadLocal<String> TRACE_ID_TTL = new TransmittableThreadLocal<>();

    /**
     * 日志详情 TTL
     */
    private static final TransmittableThreadLocal<AuditLogDetails> LOG_DETAILS_TTL = new TransmittableThreadLocal<>();

    private LogUtil() {
    }

    /**
     * 获取日志详情
     *
     * @return top.jiaojinxin.system.log.model.log.AuditLogDetails
     */
    public static AuditLogDetails getLogDetails() {
        return LOG_DETAILS_TTL.get();
    }

    /**
     * 设置日志详情
     *
     * @param logDetails 日志详情
     */
    public static void setLogDetails(AuditLogDetails logDetails) {
        LOG_DETAILS_TTL.set(logDetails);
    }

    /**
     * 清除日志详情
     */
    public static void clearLogDetails() {
        LOG_DETAILS_TTL.remove();
    }

    /**
     * 获取日志链路追踪标识
     *
     * @return java.lang.String
     */
    public static String getTraceId() {
        return TRACE_ID_TTL.get();
    }

    /**
     * 设置日志链路追踪标识
     *
     * @param traceId 日志链路追踪标识
     */
    public static void setTraceId(String traceId) {
        if (StrUtil.isBlank(traceId)) {
            traceId = generateTraceId();
        }
        TRACE_ID_TTL.set(traceId);
    }

    /**
     * 清除日志链路追踪标识
     */
    public static void clearTraceId() {
        TRACE_ID_TTL.remove();
    }

    /**
     * 构建日志链路追踪标识
     *
     * @return java.lang.String
     */
    private static String generateTraceId() {
        return IdUtil.fastSimpleUUID();
    }
}
