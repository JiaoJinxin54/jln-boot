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

    /**
     * 自动装配
     */
    public CoreAutoConfiguration() {
    }

    /**
     * 基于{@link MessageSource}的{@link I18nCodeHolder}实现
     *
     * @param messageSource {@link MessageSource}
     * @return top.jiaojinxin.core.i18n.I18nCodeHolder
     */
    @Bean
    @ConditionalOnMissingBean(I18nCodeHolder.class)
    public I18nCodeHolder MessageSourceRespCodeHolder(MessageSource messageSource) {
        return (locale, code, args) -> messageSource.getMessage(code, args, code, locale);
    }

    /**
     * 将{@link ResponseI18nCodeProperties}与{@link I18nCodeHolder}注入静态工具类，以便静态调用
     *
     * @param prop   {@link ResponseI18nCodeProperties}
     * @param holder {@link I18nCodeHolder}
     * @return org.springframework.beans.factory.SmartInitializingSingleton
     */
    @Bean
    public SmartInitializingSingleton respCodeSmartInitializingSingleton(ResponseI18nCodeProperties prop, I18nCodeHolder holder) {
        return () -> {
            CoreUtil.setResponseI18nCodeProperties(prop);
            CoreUtil.setI18nCodeHolder(holder);
        };
    }
}
