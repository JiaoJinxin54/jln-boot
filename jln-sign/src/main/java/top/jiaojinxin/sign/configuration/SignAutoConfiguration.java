package top.jiaojinxin.sign.configuration;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import top.jiaojinxin.sign.ClientPublicKeyHolder;
import top.jiaojinxin.sign.properties.SignProperties;
import top.jiaojinxin.sign.util.SignUtil;

/**
 * 签名自动装配
 *
 * @author JiaoJinxin
 */
@EnableConfigurationProperties(SignProperties.class)
public class SignAutoConfiguration {

    /**
     * 签名自动装配
     */
    public SignAutoConfiguration() {
    }

    /**
     * 默认的{@link ClientPublicKeyHolder}实现
     *
     * @return top.jiaojinxin.sign.ClientPublicKeyHolder
     */
    @Bean
    @ConditionalOnMissingBean(ClientPublicKeyHolder.class)
    public ClientPublicKeyHolder clientPublicKeyHolder() {
        return clientCode -> "";
    }

    /**
     * 将{@link SignProperties}与{@link ClientPublicKeyHolder}注入静态工具类，以便静态调用
     *
     * @param signProperties        {@link SignProperties}
     * @param clientPublicKeyHolder {@link ClientPublicKeyHolder}
     * @return org.springframework.beans.factory.SmartInitializingSingleton
     */
    @Bean
    public SmartInitializingSingleton signSmartInitializingSingleton(SignProperties signProperties, ClientPublicKeyHolder clientPublicKeyHolder) {
        return () -> {
            SignUtil.setSignProperties(signProperties);
            SignUtil.setClientPublicKeyHolder(clientPublicKeyHolder);
        };
    }
}
