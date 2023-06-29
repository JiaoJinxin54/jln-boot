package top.jiaojinxin.core.configuration;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import top.jiaojinxin.core.i18n.I18nCodeHolder;
import top.jiaojinxin.core.properties.ResponseI18nCodeProperties;
import top.jiaojinxin.core.util.CoreUtil;

/**
 * 自动装配
 *
 * @author JiaoJinxin
 */
@EnableConfigurationProperties(ResponseI18nCodeProperties.class)
public class CoreAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(I18nCodeHolder.class)
    public I18nCodeHolder MessageSourceRespCodeHolder(MessageSource messageSource) {
        return (locale, code, defaultMsg, args) -> messageSource.getMessage(code, args, defaultMsg, locale);
    }

    @Bean
    public SmartInitializingSingleton respCodeSmartInitializingSingleton(ResponseI18nCodeProperties prop, I18nCodeHolder holder) {
        return () -> {
            CoreUtil.setResponseI18nCodeProperties(prop);
            CoreUtil.setI18nCodeHolder(holder);
        };
    }
}
