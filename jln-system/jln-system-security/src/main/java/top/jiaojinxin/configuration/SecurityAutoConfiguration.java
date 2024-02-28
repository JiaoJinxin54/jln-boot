package top.jiaojinxin.configuration;

import lombok.NonNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.jiaojinxin.security.SecurityIdempotentCache;
import top.jiaojinxin.security.IdempotentHandlerInterceptor;
import top.jiaojinxin.security.SignVerifyHandlerInterceptor;
import top.jiaojinxin.common.properties.SecurityProperties;
import top.jiaojinxin.common.properties.SignProperties;

/**
 * 安全自动装配
 *
 * @author JiaoJinxin
 */
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityAutoConfiguration {

    private static final int SIGN_ORDERED = Ordered.HIGHEST_PRECEDENCE;

    private static final int IDEMPOTENT_ORDERED = SIGN_ORDERED + 1;

    @Bean
    @ConditionalOnMissingBean(SecurityIdempotentCache.class)
    public SecurityIdempotentCache idempotentCache() {
        return (key, value, duration) -> Boolean.TRUE;
    }

    @Bean
    public WebMvcConfigurer idempotentWebMvcConfigurer(SignProperties signProperties, SecurityProperties securityProperties, SecurityIdempotentCache cache) {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(@NonNull InterceptorRegistry registry) {
                // 签名拦截器
                if (securityProperties.getSign().isEnable()) {
                    registry.addInterceptor(new SignVerifyHandlerInterceptor(signProperties))
                            .addPathPatterns(securityProperties.getSign().getAddPath())
                            .excludePathPatterns(securityProperties.getSign().getExcludePath())
                            .order(SIGN_ORDERED);
                }
                // 幂等拦截器
                if (securityProperties.getIdempotent().isEnable()) {
                    registry.addInterceptor(new IdempotentHandlerInterceptor(cache, signProperties, securityProperties))
                            .addPathPatterns(securityProperties.getIdempotent().getAddPath())
                            .excludePathPatterns(securityProperties.getIdempotent().getExcludePath())
                            .order(IDEMPOTENT_ORDERED);
                }
            }
        };
    }
}
