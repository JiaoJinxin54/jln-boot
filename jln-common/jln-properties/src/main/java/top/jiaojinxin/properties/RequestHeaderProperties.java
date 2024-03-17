package top.jiaojinxin.properties;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serial;
import java.io.Serializable;

/**
 * 请求头配置
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
@ConfigurationProperties(prefix = RequestHeaderProperties.PREFIX)
public class RequestHeaderProperties implements Serializable {
    @Serial
    private static final long serialVersionUID = -5144525476426914065L;

    /**
     * 前缀
     */
    public static final String PREFIX = "jln.request.header";

    /**
     * 连接符，默认：-
     */
    private String joint = StrUtil.DASHED;

    /**
     * 前缀，默认：""
     */
    private String prefix = StrUtil.EMPTY;

    /**
     * 客户端，默认：client-code
     */
    private String client = "client-code";

    /**
     * 加密算法，用于配合加密算法盐值对参数文本进行加密，默认：algorithm
     */
    private String algorithm = "algorithm";

    /**
     * 加密算法盐值，用于配合加密算法对参数文本进行加密，该盐值为私钥加密后的密文，使用时需要使用公钥解密，默认：slat
     */
    private String salt = "salt";

    /**
     * 时间戳，默认：timestamp
     */
    private String timestamp = "timestamp";

    /**
     * 唯一键，默认：uid
     */
    private String uid = "uid";

    /**
     * 签名值，默认：sign
     */
    private String sign = "sign";

    /**
     * 日志链路追踪标识，默认：trace
     */
    private String trace = "trace";
}
