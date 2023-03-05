package top.jiaojinxin.jln.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import top.jiaojinxin.jln.properties.JlnRedisProperties;
import top.jiaojinxin.jln.util.RedisManager;

/**
 * Redis相关自动注册
 *
 * @author JiaoJinxin
 */
@EnableConfigurationProperties(JlnRedisProperties.class)
public class JlnRedisAutoRegistration {

    /**
     * 将{@link JlnRedisProperties}注册到静态工具类中，以便在非Spring Bean场景中静态调用
     *
     * @param jlnRedisProperties {@link JlnRedisProperties}
     * @author JiaoJinxin
     */
    @Autowired
    public void setJlnRedisProperties(JlnRedisProperties jlnRedisProperties) {
        RedisManager.setJlnRedisProperties(jlnRedisProperties);
    }

    /**
     * 将{@link RedisTemplate}注册到静态工具类中，以便在非Spring Bean场景中静态调用
     *
     * @param stringRedisTemplate {@link RedisTemplate}
     * @author JiaoJinxin
     */
    @Autowired
    public void setRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        RedisManager.setRedisTemplate(stringRedisTemplate);
    }
}
