package top.jiaojinxin.jln.util;

import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import top.jiaojinxin.jln.properties.JlnRedisProperties;

/**
 * Redis静态管理类
 *
 * @author JiaoJinxin
 */
public class RedisManager {
    private RedisManager() {
    }

    private static JlnRedisProperties jlnRedisProperties;

    /**
     * 获取{@link RedisManager#jlnRedisProperties}
     *
     * @return top.jiaojinxin.jln.properties.JlnRedisProperties
     * @author JiaoJinxin
     */
    public static JlnRedisProperties getJlnRedisProperties() {
        return jlnRedisProperties;
    }

    /**
     * 设置{@link RedisManager#jlnRedisProperties}
     *
     * @param jlnRedisProperties {@link RedisManager#jlnRedisProperties}
     * @author JiaoJinxin
     */
    public static void setJlnRedisProperties(JlnRedisProperties jlnRedisProperties) {
        RedisManager.jlnRedisProperties = jlnRedisProperties;
    }

    private static RedisTemplate<String, String> redisTemplate;

    /**
     * 获取{@link RedisManager#redisTemplate}
     *
     * @return org.springframework.data.redis.core.RedisTemplate
     * @author JiaoJinxin
     */
    public static RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * 获取{@link RedisManager#redisTemplate}
     *
     * @param redisTemplate {@link RedisManager#redisTemplate}
     * @author JiaoJinxin
     */
    public static void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        RedisManager.redisTemplate = redisTemplate;
    }

    /**
     * 获取{@link ValueOperations}
     *
     * @return org.springframework.data.redis.core.ValueOperations
     * @author JiaoJinxin
     */
    public static ValueOperations<String, String> value() {
        return getRedisTemplate().opsForValue();
    }

    /**
     * 获取{@link HashOperations}
     *
     * @return org.springframework.data.redis.core.HashOperations
     * @author JiaoJinxin
     */
    public static HashOperations<String, String, String> hash() {
        return getRedisTemplate().opsForHash();
    }

    /**
     * 获取{@link ListOperations}
     *
     * @return org.springframework.data.redis.core.ListOperations
     * @author JiaoJinxin
     */
    public static ListOperations<String, String> list() {
        return getRedisTemplate().opsForList();
    }

    /**
     * 获取{@link SetOperations}
     *
     * @return org.springframework.data.redis.core.SetOperations
     * @author JiaoJinxin
     */
    public static SetOperations<String, String> set() {
        return getRedisTemplate().opsForSet();
    }

    /**
     * 获取{@link ZSetOperations}
     *
     * @return org.springframework.data.redis.core.ZSetOperations
     * @author JiaoJinxin
     */
    public static ZSetOperations<String, String> zSet() {
        return getRedisTemplate().opsForZSet();
    }

    /**
     * 获取{@link GeoOperations}
     *
     * @return org.springframework.data.redis.core.GeoOperations
     * @author JiaoJinxin
     */
    public static GeoOperations<String, String> geo() {
        return getRedisTemplate().opsForGeo();
    }
}
