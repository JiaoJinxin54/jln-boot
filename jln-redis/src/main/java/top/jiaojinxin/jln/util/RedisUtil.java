package top.jiaojinxin.jln.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import top.jiaojinxin.jln.properties.JlnRedisProperties;

import java.time.Duration;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * Redis工具类
 *
 * @author JiaoJinxin
 */
public class RedisUtil {

    private static final Logger log = LoggerFactory.getLogger(RedisUtil.class);

    /**
     * 默认等待时间0，即默认不等待
     */
    private static final long DEF_WAIT = 0;

    /**
     * 默认值
     */
    private static final String DEF_VALUE = "0";

    /**
     * 随机数
     */
    private static final Random RANDOM = new Random();

    /**
     * 默认执行程序
     */
    private static final Runnable DEF_RUNNABLE = () -> {
    };

    private RedisUtil() {
    }

    /**
     * 获取随机过期时间<br/>
     * 默认过期时间 + 20（不含）以内的随机数
     *
     * @return long
     * @author JiaoJinxin
     */
    public static long getExpire() {
        return getExpire(20L);
    }

    /**
     * 获取随机过期时间<br/>
     * 默认过期时间 + 指定值（不含）以内的随机数
     *
     * @param bound 返回值的上限(不含)
     * @return long
     * @author JiaoJinxin
     */
    public static long getExpire(long bound) {
        JlnRedisProperties jlnRedisProperties = RedisManager.getJlnRedisProperties();
        return jlnRedisProperties.getExpire() + RANDOM.nextLong(bound);
    }

    /**
     * 立即获取锁（使用默认的过期时间），并根据获取锁的结果执行相应的业务逻辑（请求失败时使用默认执行程序，无返回值逻辑）
     *
     * @param key     锁的key
     * @param success 加锁成功的业务逻辑
     * @author JiaoJinxin
     */
    public static void lock(String key, Runnable success) {
        lock(key, success, DEF_RUNNABLE);
    }

    /**
     * 立即获取锁（使用默认的过期时间），并根据获取锁的结果执行相应的业务逻辑（无返回值逻辑）
     *
     * @param key     锁的key
     * @param success 加锁成功的业务逻辑
     * @param fail    加锁失败的业务逻辑
     * @author JiaoJinxin
     */
    public static void lock(String key, Runnable success, Runnable fail) {
        JlnRedisProperties jlnRedisProperties = RedisManager.getJlnRedisProperties();
        lock(key, jlnRedisProperties.getExpire(), jlnRedisProperties.getTimeUnit(), success, fail);
    }

    /**
     * 立即获取锁，并根据获取锁的结果执行相应的业务逻辑（无返回值逻辑）
     *
     * @param key        锁的key
     * @param expireTime 过期时间
     * @param timeUnit   过期时间单位
     * @param success    加锁成功的业务逻辑
     * @param fail       加锁失败的业务逻辑
     * @author JiaoJinxin
     */
    public static void lock(String key, Long expireTime, TimeUnit timeUnit, Runnable success, Runnable fail) {
        lock(key, expireTime, DEF_WAIT, timeUnit, success, fail);
    }

    /**
     * 在指定时间内获取锁，并根据获取锁的结果执行相应的业务逻辑（无返回值逻辑）
     *
     * @param key        锁的key
     * @param expireTime 过期时间
     * @param waitTime   等待时间
     * @param timeUnit   过期时间单位
     * @param success    加锁成功的业务逻辑
     * @param fail       加锁失败的业务逻辑
     * @author JiaoJinxin
     */
    public static void lock(String key, Long expireTime, Long waitTime, TimeUnit timeUnit, Runnable success, Runnable fail) {
        // 键、时间单位、过期时间为空 或 过期时间小于等于0时，不做处理直接返回
        if (!StringUtils.hasText(key) || timeUnit == null || expireTime == null || expireTime <= 0) {
            return;
        }
        LogUtil.debug(log, String.format("加锁执行开始（%s）......", key));
        if (getLock(key, expireTime, waitTime, timeUnit)) {
            // 加锁成功的业务逻辑
            try {
                LogUtil.debug(log, String.format("加锁成功，准备执行加锁成功的业务逻辑（%s）......", key));
                Optional.ofNullable(success).orElse(DEF_RUNNABLE).run();
                LogUtil.debug(log, String.format("执行加锁成功的业务逻辑结束（%s）......", key));
            } finally {
                // 解锁
                RedisManager.getRedisTemplate().delete(key);
                LogUtil.debug(log, String.format("解锁完成（%s）......", key));
            }
        } else {
            // 加锁失败的业务逻辑
            LogUtil.debug(log, String.format("加锁失败，准备执行加锁失败的业务逻辑（%s）......", key));
            Optional.ofNullable(fail).orElse(DEF_RUNNABLE).run();
            LogUtil.debug(log, String.format("执行加锁失败的业务逻辑结束（%s）......", key));
        }
        LogUtil.debug(log, String.format("加锁执行结束（%s）......", key));
    }

    /**
     * 循环等待解锁（几乎无限等待），解锁成功后执行指定逻辑（无返回值逻辑）
     *
     * @param key      锁的key
     * @param runnable 执行程序（无返回值逻辑）
     * @author JiaoJinxin
     */
    public static void waitUnlockAndRun(String key, Runnable runnable) {
        waitUnlockAndRun(key, runnable, Long.MAX_VALUE, TimeUnit.SECONDS);
    }

    /**
     * 循环等待解锁（在指定时间内），解锁成功时执行指定逻辑（无返回值逻辑）
     *
     * @param key      锁的key
     * @param runnable 执行程序（无返回值逻辑）
     * @param waitTime 等待时间
     * @param timeUnit 时间单位
     * @author JiaoJinxin
     */
    public static void waitUnlockAndRun(String key, Runnable runnable, Long waitTime, TimeUnit timeUnit) {
        LogUtil.debug(log, String.format("等待解锁（%s）......", key));
        if (checkUnlock(key, waitTime, timeUnit)) {
            LogUtil.debug(log, String.format("指定时间内（%s %s），解锁成功，准备执行指定逻辑（%s）......", waitTime, timeUnit.name(), key));
            runnable.run();
            LogUtil.debug(log, String.format("执行指定逻辑结束（%s）......", key));
        } else {
            LogUtil.debug(log, String.format("指定时间内（%s %s），解锁失败，不做任何处理（%s）......", waitTime, timeUnit.name(), key));
        }
    }

    /**
     * 立即获取锁（使用默认的过期时间），并根据获取锁的结果执行相应的业务逻辑（有返回值逻辑）
     *
     * @param key     锁的key
     * @param success 加锁成功的业务逻辑
     * @param fail    加锁失败的业务逻辑
     * @author JiaoJinxin
     */
    public static <T> T lock(String key, Supplier<T> success, Supplier<T> fail) {
        JlnRedisProperties jlnRedisProperties = RedisManager.getJlnRedisProperties();
        return lock(key, jlnRedisProperties.getExpire(), jlnRedisProperties.getTimeUnit(), success, fail);
    }

    /**
     * 立即获取锁，并根据获取锁的结果执行相应的业务逻辑（有返回值逻辑）
     *
     * @param key        锁的key
     * @param expireTime 过期时间
     * @param timeUnit   过期时间单位
     * @param success    加锁成功的业务逻辑
     * @param fail       加锁失败的业务逻辑
     * @author JiaoJinxin
     */
    public static <T> T lock(String key, Long expireTime, TimeUnit timeUnit, Supplier<T> success, Supplier<T> fail) {
        return lock(key, expireTime, DEF_WAIT, timeUnit, success, fail);
    }

    /**
     * 在指定时间内获取锁，并根据获取锁的结果执行相应的业务逻辑（有返回值逻辑）
     *
     * @param key        锁的key
     * @param expireTime 过期时间
     * @param waitTime   等待时间
     * @param timeUnit   过期时间单位
     * @param success    加锁成功的业务逻辑
     * @param fail       加锁失败的业务逻辑
     * @author JiaoJinxin
     */
    public static <T> T lock(String key, Long expireTime, Long waitTime, TimeUnit timeUnit, Supplier<T> success, Supplier<T> fail) {
        // 键、时间单位、过期时间为空 或 过期时间小于等于0时，不做处理直接返回
        if (!StringUtils.hasText(key) || timeUnit == null || expireTime == null || expireTime <= 0) {
            return null;
        }
        LogUtil.debug(log, String.format("加锁执行开始（%s）......", key));
        Supplier<T> defSupplier = () -> null;
        T result;
        if (getLock(key, expireTime, waitTime, timeUnit)) {
            // 加锁成功的业务逻辑
            try {
                LogUtil.debug(log, String.format("加锁成功，准备执行加锁成功的业务逻辑（%s）......", key));
                result = Optional.ofNullable(success).orElse(defSupplier).get();
                LogUtil.debug(log, String.format("执行加锁成功的业务逻辑结束（%s）......", key));
            } finally {
                // 解锁
                RedisManager.getRedisTemplate().delete(key);
                LogUtil.debug(log, String.format("解锁完成（%s）......", key));
            }
        } else {
            // 加锁失败的业务逻辑
            LogUtil.debug(log, String.format("加锁失败，准备执行加锁失败的业务逻辑（%s）......", key));
            result = Optional.ofNullable(fail).orElse(defSupplier).get();
            LogUtil.debug(log, String.format("执行加锁失败的业务逻辑结束（%s）......", key));
        }
        LogUtil.debug(log, String.format("加锁执行结束（%s）......", key));
        return result;
    }

    /**
     * 循环等待解锁（无限等待），解锁成功后执行指定逻辑（有返回值逻辑）
     *
     * @param key      锁的key
     * @param supplier 执行程序（有返回值逻辑）
     * @param <T>      返回值类型
     * @return T
     * @author JiaoJinxin
     */
    public static <T> T waitUnlockAndRun(String key, Supplier<T> supplier) {
        JlnRedisProperties jlnRedisProperties = RedisManager.getJlnRedisProperties();
        return waitUnlockAndRun(key, supplier, Long.valueOf(Integer.MAX_VALUE), jlnRedisProperties.getTimeUnit());
    }

    /**
     * 循环等待解锁（在指定时间内），解锁成功时执行指定逻辑（有返回值逻辑）
     *
     * @param key      锁的key
     * @param supplier 执行程序（有返回值逻辑）
     * @param waitTime 等待时间
     * @param timeUnit 时间单位
     * @param <T>      返回值类型
     * @return T
     * @author JiaoJinxin
     */
    public static <T> T waitUnlockAndRun(String key, Supplier<T> supplier, Long waitTime, TimeUnit timeUnit) {
        LogUtil.debug(log, String.format("等待解锁（%s）......", key));
        T result = null;
        if (checkUnlock(key, waitTime, timeUnit)) {
            LogUtil.debug(log, String.format("指定时间内（%s %s），解锁成功，准备执行指定逻辑（%s）......", waitTime, timeUnit.name(), key));
            result = supplier.get();
            LogUtil.debug(log, String.format("执行指定逻辑结束（%s）......", key));
        } else {
            LogUtil.debug(log, String.format("指定时间内（%s %s），解锁失败，不做任何处理（%s）......", waitTime, timeUnit.name(), key));
        }
        return result;
    }

    /**
     * 判断是否已解锁
     *
     * @param key      锁的key
     * @param waitTime 等待时间
     * @param timeUnit 过期时间单位
     * @return boolean
     * @author JiaoJinxin
     */
    private static boolean checkUnlock(String key, Long waitTime, TimeUnit timeUnit) {
        // 持有锁时循环等待，在指定时间内结束等待则表示已解锁
        return !cyclicWait(() -> doHasLock(key), waitTime, timeUnit);
    }

    /**
     * 判断是否持有锁
     *
     * @param key 锁的key
     * @return boolean
     * @author JiaoJinxin
     */
    private static boolean doHasLock(String key) {
        Boolean hasKey = RedisManager.getRedisTemplate().hasKey(key);
        LogUtil.debug(log, String.format("尝试判断锁是否存在（%s）......", key));
        return Boolean.TRUE.equals(hasKey);
    }

    /**
     * 获取锁
     *
     * @param key        锁的key
     * @param expireTime 过期时间
     * @param waitTime   等待时间
     * @param timeUnit   过期时间单位
     * @author JiaoJinxin
     */
    private static boolean getLock(String key, Long expireTime, Long waitTime, TimeUnit timeUnit) {
        // 未获取到锁时循环等待，在指定时间内结束等待则表示成功获取锁
        return !cyclicWait(() -> !doGetLock(key, expireTime, timeUnit), waitTime, timeUnit);
    }

    /**
     * 加锁程序
     *
     * @param key        锁的key
     * @param expireTime 过期时间
     * @param timeUnit   过期时间单位
     * @return boolean
     * @author JiaoJinxin
     */
    private static boolean doGetLock(String key, Long expireTime, TimeUnit timeUnit) {
        Boolean locked = RedisManager.value().setIfAbsent(key, DEF_VALUE, expireTime, timeUnit);
        LogUtil.debug(log, String.format("尝试加锁（%s）......", key));
        return Boolean.TRUE.equals(locked);
    }

    /**
     * 循环等待
     *
     * @param waitCondition 等待条件（为true时等待）
     * @param waitTime      等待时间
     * @param timeUnit      过期时间单位
     * @return boolean
     * @author JiaoJinxin
     */
    private static boolean cyclicWait(BooleanSupplier waitCondition, Long waitTime, TimeUnit timeUnit) {
        JlnRedisProperties jlnRedisProperties = RedisManager.getJlnRedisProperties();
        // 等待时间（毫秒），小于等于0时默认不等待
        long wait = toDuration(Math.max(Optional.ofNullable(waitTime).orElse(DEF_WAIT), DEF_WAIT), timeUnit).toMillis();
        // 默认的休眠时间（毫秒）
        long fixedSleep = toDuration(jlnRedisProperties.getSleep(), jlnRedisProperties.getTimeUnit()).toMillis();
        // 休眠时间
        long sleep;
        // 是否等待
        boolean isWait = false;
        // 加锁
        try {
            while ((isWait = waitCondition.getAsBoolean()) && wait > 0) {
                LogUtil.debug(log, String.format("继续等待，剩余等待时间（%s %s）......", wait, timeUnit.name()));
                sleep = Math.min(wait, fixedSleep);
                TimeUnit.MILLISECONDS.sleep(sleep);
                wait -= sleep;
            }
        } catch (InterruptedException ignored) {
        }
        return isWait;
    }

    /**
     * 将时间值与单位转换为{@link Duration}
     *
     * @param time     时间值
     * @param timeUnit 时间单位
     * @return java.time.Duration
     * @author JiaoJinxin
     */
    private static Duration toDuration(long time, TimeUnit timeUnit) {
        return switch (timeUnit) {
            case DAYS -> Duration.ofDays(time);
            case HOURS -> Duration.ofHours(time);
            case MINUTES -> Duration.ofMinutes(time);
            case SECONDS -> Duration.ofSeconds(time);
            case MILLISECONDS -> Duration.ofMillis(time);
            case MICROSECONDS -> Duration.ofNanos(time * 1000);
            case NANOSECONDS -> Duration.ofNanos(time);
        };
    }
}