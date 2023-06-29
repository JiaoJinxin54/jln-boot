package top.jiaojinxin.sign.configuration;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import top.jiaojinxin.sign.properties.SignProperties;
import top.jiaojinxin.sign.ClientPublicKeyHolder;
import top.jiaojinxin.sign.util.SignUtil;

/**
 * 签名自动装配
 *
 * @author JiaoJinxin
 */
@EnableConfigurationProperties(SignProperties.class)
public class SignAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ClientPublicKeyHolder.class)
    public ClientPublicKeyHolder clientPublicKeyHolder() {
        return clientCode -> "";
    }

    @Bean
    public SmartInitializingSingleton signSmartInitializingSingleton(SignProperties signProperties, ClientPublicKeyHolder clientPublicKeyHolder) {
        return () -> {
            SignUtil.setSignProperties(signProperties);
            SignUtil.setClientPublicKeyHolder(clientPublicKeyHolder);
        };
    }
}
