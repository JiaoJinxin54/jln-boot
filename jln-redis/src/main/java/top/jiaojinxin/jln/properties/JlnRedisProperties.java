package top.jiaojinxin.jln.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

/**
 * Redis需要配置的参数
 *
 * @author JiaoJinxin
 */
@ConfigurationProperties(prefix = "jln.redis")
public class JlnRedisProperties {

    /**
     * 过期时间，时间单位采用{@link JlnRedisProperties#timeUnit}，默认：30000
     */
    private Long expire = 30000L;

    /**
     * 循环等待间隔时间，时间单位采用{@link JlnRedisProperties#timeUnit}，默认：100
     */
    private Long sleep = 100L;

    /**
     * 时间单位，默认：毫秒
     */
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

    /**
     * 构造方法
     *
     * @author JiaoJinxin
     */
    public JlnRedisProperties() {
    }

    /**
     * 获取{@link JlnRedisProperties#expire}
     *
     * @return java.lang.Long
     * @author JiaoJinxin
     */
    public Long getExpire() {
        return expire;
    }

    /**
     * 设置{@link JlnRedisProperties#expire}
     *
     * @param expire {@link JlnRedisProperties#expire}
     * @author JiaoJinxin
     */
    public void setExpire(Long expire) {
        this.expire = expire;
    }

    /**
     * 获取{@link JlnRedisProperties#sleep}
     *
     * @return java.lang.Long
     * @author JiaoJinxin
     */
    public Long getSleep() {
        return sleep;
    }

    /**
     * 设置{@link JlnRedisProperties#sleep}
     *
     * @param sleep {@link JlnRedisProperties#sleep}
     * @author JiaoJinxin
     */
    public void setSleep(Long sleep) {
        this.sleep = sleep;
    }

    /**
     * 获取{@link JlnRedisProperties#timeUnit}
     *
     * @return java.util.concurrent.TimeUnit
     * @author JiaoJinxin
     */
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    /**
     * 设置{@link JlnRedisProperties#timeUnit}
     *
     * @param timeUnit {@link JlnRedisProperties#timeUnit}
     * @author JiaoJinxin
     */
    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}
