package top.jiaojinxin.common.configuration;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import top.jiaojinxin.common.properties.RequestHeaderProperties;
import top.jiaojinxin.common.properties.ResponseI18nCodeProperties;
import top.jiaojinxin.common.util.PropertiesManager;

/**
 * 自动装配
 *
 * @author JiaoJinxin
 */
@EnableConfigurationProperties({
        RequestHeaderProperties.class,
        ResponseI18nCodeProperties.class
})
public class PropertiesAutoConfiguration {

    /**
     * 自动装配
     */
    public PropertiesAutoConfiguration() {
    }

    /**
     * 将properties配置注入静态工具类，以便静态调用
     *
     * @param requestHeaderProperties    {@link RequestHeaderProperties}
     * @param responseI18nCodeProperties {@link ResponseI18nCodeProperties}
     * @return org.springframework.beans.factory.SmartInitializingSingleton
     */
    @Bean
    public SmartInitializingSingleton propertiesSmartInitializingSingleton(
            RequestHeaderProperties requestHeaderProperties,
            ResponseI18nCodeProperties responseI18nCodeProperties) {
        return () -> {
            PropertiesManager.setRequestHeaderProperties(requestHeaderProperties);
            PropertiesManager.setResponseI18nCodeProperties(responseI18nCodeProperties);
        };
    }
}
