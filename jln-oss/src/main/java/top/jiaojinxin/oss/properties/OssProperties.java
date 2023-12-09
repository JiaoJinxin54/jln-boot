package top.jiaojinxin.oss.properties;

import com.amazonaws.Protocol;
import com.amazonaws.regions.Regions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

import static top.jiaojinxin.oss.properties.OssProperties.PREFIX;

/**
 * oss对象存储配置
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
@ConfigurationProperties(prefix = PREFIX)
public class OssProperties implements Serializable {
    private static final long serialVersionUID = 8794463162369064043L;

    public static final String PREFIX = "jln.oss";

    /**
     * 协议
     */
    private Protocol protocol = Protocol.HTTPS;

    /**
     * oss服务地址
     */
    private String serviceEndpoint;

    /**
     * 区域
     */
    private String signingRegion = Regions.CN_NORTH_1.getName();

    /**
     * 访问令牌
     */
    private String accessKey;

    /**
     * 访问密钥
     */
    private String secretKey;

    /**
     * 桶名称
     */
    private String bucketName;
}
