package top.jiaojinxin.security;

import java.time.Duration;

/**
 * 幂等缓存
 *
 * @author JiaoJinxin
 */
public interface SecurityIdempotentCache {

    /**
     * 若不存在时设置幂等值，设置成功时返回true
     *
     * @param key      键
     * @param value    值
     * @param duration 有效期
     * @return boolean
     */
    boolean setIfAbsent(String key, Object value, Duration duration);
}
