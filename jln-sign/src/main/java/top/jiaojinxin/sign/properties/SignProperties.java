package top.jiaojinxin.sign.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serial;
import java.io.Serializable;

import static top.jiaojinxin.sign.properties.SignProperties.PREFIX;

/**
 * 签名配置
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
@ConfigurationProperties(prefix = PREFIX)
public class SignProperties implements Serializable {
    @Serial
    private static final long serialVersionUID = 4879394334941174281L;

    public static final String PREFIX = "jln.sign";

    /**
     * 客户端标识
     */
    private String clientCode;

    /**
     * 客户端私钥
     */
    private String clientPrivateKey;

    /**
     * 异常码
     */
    @NestedConfigurationProperty
    private SignExceptionCode exceptionCode = new SignExceptionCode();
}
