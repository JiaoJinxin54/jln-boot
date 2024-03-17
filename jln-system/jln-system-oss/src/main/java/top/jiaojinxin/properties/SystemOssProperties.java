package top.jiaojinxin.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serial;

/**
 * 系统对象存储配置
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
@ConfigurationProperties(prefix = SystemOssProperties.PREFIX)
public class SystemOssProperties extends OssProperties {
    @Serial
    private static final long serialVersionUID = -6015733401847592595L;

    /**
     * 前缀
     */
    public static final String PREFIX = "jln.oss.web";

    /**
     * 接口基础路径
     */
    private String basePath;

    /**
     * 获取上传文件签名URL接口地址
     */
    private String generateUpload;

    /**
     * 获取下载文件签名URL接口地址
     */
    private String generateDownload;
}
