package top.jiaojinxin.jln.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import top.jiaojinxin.jln.oss.DefaultOssTemplateImpl;
import top.jiaojinxin.jln.oss.OssTemplate;
import top.jiaojinxin.jln.properties.JlnOssProperties;

/**
 * oss对象存储自动装配
 *
 * @author JiaoJinxin
 */
@EnableConfigurationProperties(JlnOssProperties.class)
public class JlnOssAutoConfiguration {

    /**
     * oss对象存储模板不存在时，注册默认实现
     *
     * @param ossProperties oss对象存储配置
     * @return top.jiaojinxin.jln.oss.OssTemplate
     * @author JiaoJinxin
     */
    @Bean
    @ConditionalOnMissingBean(OssTemplate.class)
    @ConditionalOnProperty(value = "jln.oss.enable", havingValue = "true")
    public OssTemplate ossTemplate(JlnOssProperties ossProperties) {
        return new DefaultOssTemplateImpl(ossProperties);
    }
}
