package top.jiaojinxin.system.configuration;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import top.jiaojinxin.system.properties.SystemOssProperties;
import top.jiaojinxin.oss.configuration.OssAutoConfiguration;

/**
 * 对象存储配置
 *
 * @author JiaoJinxin
 */
@ComponentScan("top.jiaojinxin.jln.system.oss.web")
@AutoConfigureBefore(OssAutoConfiguration.class)
@EnableConfigurationProperties(SystemOssProperties.class)
public class OssConfiguration {
}
