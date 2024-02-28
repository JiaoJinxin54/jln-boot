package top.jiaojinxin.system.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import top.jiaojinxin.oss.properties.OssProperties;

import java.io.Serial;
import java.io.Serializable;

/**
 * 系统对象存储配置
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
@ConfigurationProperties(prefix = OssProperties.PREFIX)
public class SystemOssProperties implements Serializable {
    @Serial
    private static final long serialVersionUID = -6015733401847592595L;

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
