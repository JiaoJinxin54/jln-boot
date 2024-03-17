package top.jiaojinxin.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import top.jiaojinxin.oss.DefaultOssTemplateImpl;
import top.jiaojinxin.oss.OssTemplate;
import top.jiaojinxin.properties.OssProperties;

/**
 * oss对象存储自动装配
 *
 * @author JiaoJinxin
 */
@EnableConfigurationProperties(OssProperties.class)
public class OssAutoConfiguration {

    /**
     * oss对象存储自动装配
     */
    public OssAutoConfiguration() {
    }

    /**
     * oss对象存储模板不存在时，注册默认实现
     *
     * @param ossProperties oss对象存储配置
     * @return top.jiaojinxin.oss.OssTemplate
     */
    @Bean
    @ConditionalOnMissingBean(OssTemplate.class)
    public OssTemplate ossTemplate(OssProperties ossProperties) {
        return new DefaultOssTemplateImpl(ossProperties);
    }
}
