package top.jiaojinxin.system.log.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 审计日志详情
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
public class AuditLogDetails implements Serializable {
    @Serial
    private static final long serialVersionUID = -7853702815117873023L;

    /**
     * 操作
     */
    private String operation;

    /**
     * 类型
     */
    private String type;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 请求url
     */
    private String url;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 请求开始时间（精确至毫秒）
     */
    private LocalDateTime requestStart;

    /**
     * 请求结束时间（精确至毫秒）
     */
    private LocalDateTime requestEnd;

    /**
     * 请求耗时（单位：毫秒）
     */
    private Long requestUse;

    /**
     * 客户端ip
     */
    private String clientIp;

    /**
     * 设备
     */
    private String deviceType;

    /**
     * 操作系统
     */
    private String operatingSystem;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 浏览器版本
     */
    private String browserVersion;
}
