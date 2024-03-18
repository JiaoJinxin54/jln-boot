package top.jiaojinxin.configuration;

import cn.hutool.crypto.KeyUtil;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import top.jiaojinxin.sign.ClientSecretKeyHolder;
import top.jiaojinxin.util.SignUtil;

import java.security.KeyPair;

/**
 * 签名自动装配
 *
 * @author JiaoJinxin
 */
public class SignAutoConfiguration {

    /**
     * 签名自动装配
     */
    public SignAutoConfiguration() {
    }

    /**
     * 默认的{@link ClientSecretKeyHolder}实现
     *
     * @return top.jiaojinxin.sign.ClientPublicKeyHolder
     */
    @Bean
    @ConditionalOnMissingBean(ClientSecretKeyHolder.class)
    public ClientSecretKeyHolder clientSecretKeyHolder() {
        KeyPair keyPair = SignUtil.generateKeyPair();
        return new ClientSecretKeyHolder() {
            @Override
            public String privateKey(String clientCode) {
                return KeyUtil.toBase64(keyPair.getPrivate());
            }

            @Override
            public String publicKey(String clientCode) {
                return KeyUtil.toBase64(keyPair.getPublic());
            }
        };
    }

    /**
     * 将{@link ClientSecretKeyHolder}注入静态工具类，以便静态调用
     *
     * @param clientSecretKeyHolder {@link ClientSecretKeyHolder}
     * @return org.springframework.beans.factory.SmartInitializingSingleton
     */
    @Bean
    public SmartInitializingSingleton signSmartInitializingSingleton(ClientSecretKeyHolder clientSecretKeyHolder) {
        return () -> SignUtil.setClientSecretKeyHolder(clientSecretKeyHolder);
    }
}
