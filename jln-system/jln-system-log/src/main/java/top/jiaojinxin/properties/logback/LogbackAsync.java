package top.jiaojinxin.properties.logback;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 异步打印日志配置
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
public class LogbackAsync implements Serializable {
    @Serial
    private static final long serialVersionUID = -4004722088075289614L;

    /**
     * 循环滚动配置
     */
    public LogbackAsync() {
    }

    /**
     * 异步日志队列最大长度，默认：256
     */
    private Integer queueSize = 256;

    /**
     * 丢弃阈值，即异步日志队列容量剩余阈值大小时开始丢弃非WARN和ERROR级别日志，默认：0（即不丢弃）
     */
    private Integer discardingThreshold = 0;

    /**
     * 是否包含调用者的信息（方法名及行号），默认：true
     */
    private Boolean includeCallerData = true;
}