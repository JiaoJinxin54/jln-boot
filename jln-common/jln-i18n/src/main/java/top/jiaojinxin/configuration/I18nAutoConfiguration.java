package top.jiaojinxin.configuration;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import top.jiaojinxin.common.i18n.I18nMsgReader;
import top.jiaojinxin.util.I18nManager;

/**
 * 自动装配
 *
 * @author JiaoJinxin
 */
public class I18nAutoConfiguration {

    /**
     * 自动装配
     */
    public I18nAutoConfiguration() {
    }

    /**
     * 基于{@link MessageSource}的{@link I18nMsgReader}实现
     *
     * @param messageSource {@link MessageSource}
     * @return top.jiaojinxin.common.i18n.I18nCodeHolder
     */
    @Bean
    @ConditionalOnMissingBean(I18nMsgReader.class)
    public I18nMsgReader MessageSourceRespCodeHolder(MessageSource messageSource) {
        return (locale, code, args) -> messageSource.getMessage(code, args, code, locale);
    }

    /**
     * 将{@link I18nMsgReader}注入静态工具类，以便静态调用
     *
     * @param holder {@link I18nMsgReader}
     * @return org.springframework.beans.factory.SmartInitializingSingleton
     */
    @Bean
    public SmartInitializingSingleton i18nSmartInitializingSingleton(I18nMsgReader holder) {
        return () -> I18nManager.setI18nMsgReader(holder);
    }
}
