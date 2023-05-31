package top.jiaojinxin.jln.properties;

import com.amazonaws.regions.Regions;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * oss对象存储配置
 *
 * @author JiaoJinxin
 */
@ConfigurationProperties(prefix = "jln.oss")
public class JlnOssProperties implements Serializable {

    /**
     * 是否开启
     */
    private Boolean enable = false;

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

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getServiceEndpoint() {
        return serviceEndpoint;
    }

    public void setServiceEndpoint(String serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }

    public String getSigningRegion() {
        return signingRegion;
    }

    public void setSigningRegion(String signingRegion) {
        this.signingRegion = signingRegion;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
