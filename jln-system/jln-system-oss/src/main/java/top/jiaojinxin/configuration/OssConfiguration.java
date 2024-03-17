package top.jiaojinxin.configuration;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import top.jiaojinxin.properties.SystemOssProperties;

/**
 * 对象存储配置
 *
 * @author JiaoJinxin
 */
@ComponentScan("top.jiaojinxin.system.oss.web")
@AutoConfigureBefore(OssAutoConfiguration.class)
@EnableConfigurationProperties(SystemOssProperties.class)
public class OssConfiguration {
}
