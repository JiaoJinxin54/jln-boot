package top.jiaojinxin.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serial;
import java.io.Serializable;

import static top.jiaojinxin.properties.SecurityProperties.PREFIX;

/**
 * 安全配置
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
@ConfigurationProperties(prefix = PREFIX)
public class SecurityProperties implements Serializable {
    @Serial
    private static final long serialVersionUID = -7035866204136681364L;

    public static final String PREFIX = "jln.security";

    /**
     * 最大允许的服务器时间差异（单位：毫秒），默认：1000
     */
    private Long timeDiff = 1000L;

    /**
     * 唯一键过期时间（单位：毫秒），默认：10000
     */
    private Long uidExpire = 10000L;

    /**
     * 签名拦截器相关配置
     */
    @NestedConfigurationProperty
    private InterceptorProp sign = new InterceptorProp();

    /**
     * 幂等拦截器相关配置
     */
    @NestedConfigurationProperty
    private InterceptorProp idempotent = new InterceptorProp();
}
