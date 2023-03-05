package top.jiaojinxin.jln.util;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.util.StringUtils;
import top.jiaojinxin.jln.event.Event;
import top.jiaojinxin.jln.event.EventHandler;
import top.jiaojinxin.jln.exception.BaseException;
import top.jiaojinxin.jln.exception.BizException;
import top.jiaojinxin.jln.exception.JlnException;
import top.jiaojinxin.jln.exception.SysException;
import top.jiaojinxin.jln.respcode.RespCode;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 工具类
 *
 * @author JiaoJinxin
 */
public class JlnUtil {
    private JlnUtil() {
    }

    private static final String COMMA = ",";

    private static final String UNKNOWN = "unknown";

    // region RespCode模块

    /**
     * 获取响应码（请求执行成功）
     *
     * @return top.jiaojinxin.jln.respcode.RespCode
     * @author JiaoJinxin
     */
    public static RespCode success() {
        return RespCodeUtil.success();
    }

    /**
     * 获取响应码（请求执行失败）
     *
     * @return top.jiaojinxin.jln.respcode.RespCode
     * @author JiaoJinxin
     */
    public static RespCode fail() {
        return RespCodeUtil.fail();
    }

    /**
     * 获取响应码
     *
     * @param code 响应码code
     * @param args 响应码描述参数
     * @return top.jiaojinxin.jln.respcode.RespCode
     * @author JiaoJinxin
     */
    public static RespCode getRespCode(String code, String... args) {
        return RespCodeUtil.getRespCode(code, args);
    }

    /**
     * 获取响应码描述
     *
     * @param code 响应码code
     * @param args 响应码描述参数
     * @return java.lang.String
     * @author JiaoJinxin
     */
    public static String getMsg(String code, String... args) {
        return getRespCode(code, args).msg();
    }

    // endregion

    // region Exception模块

    /**
     * 构建业务异常
     *
     * @return top.jiaojinxin.jln.exception.BizException
     * @author JiaoJinxin
     */
    public static BizException biz() {
        return biz(null);
    }

    /**
     * 构建业务异常
     *
     * @param code 响应码code
     * @param args 响应码描述参数
     * @return top.jiaojinxin.jln.exception.BizException
     * @author JiaoJinxin
     */
    public static BizException biz(String code, String... args) {
        return ExceptionUtil.biz(code, args);
    }

    /**
     * 抛出业务异常
     *
     * @author JiaoJinxin
     */
    public static void thrBiz() throws BizException {
        ExceptionUtil.thrBiz();
    }

    /**
     * 抛出业务异常
     *
     * @param code 响应码code
     * @param args 响应码描述参数
     * @author JiaoJinxin
     */
    public static void thrBiz(String code, String... args) throws BizException {
        ExceptionUtil.thrBiz(code, args);
    }

    /**
     * 构建框架异常
     *
     * @return top.jiaojinxin.jln.exception.JlnException
     * @author JiaoJinxin
     */
    public static JlnException jln() {
        return ExceptionUtil.jln();
    }

    /**
     * 构建框架异常
     *
     * @param code 响应码code
     * @param args 响应码描述参数
     * @return top.jiaojinxin.jln.exception.JlnException
     * @author JiaoJinxin
     */
    public static JlnException jln(String code, String... args) {
        return ExceptionUtil.jln(code, args);
    }

    /**
     * 抛出框架异常
     *
     * @author JiaoJinxin
     */
    public static void thrJln() throws JlnException {
        ExceptionUtil.thrJln();
    }

    /**
     * 抛出框架异常
     *
     * @param code 响应码code
     * @param args 响应码描述参数
     * @author JiaoJinxin
     */
    public static void thrJln(String code, String... args) throws JlnException {
        ExceptionUtil.thrJln(code, args);
    }

    /**
     * 构建系统异常
     *
     * @param cause 异常
     * @return top.jiaojinxin.jln.exception.SysException
     * @author JiaoJinxin
     */
    public static SysException sys(Throwable cause) {
        return ExceptionUtil.sys(cause);
    }

    /**
     * 构建系统异常
     *
     * @param cause 异常
     * @param code  响应码code
     * @param args  响应码描述参数
     * @return top.jiaojinxin.jln.exception.SysException
     * @author JiaoJinxin
     */
    public static SysException sys(Throwable cause, String code, String... args) {
        return ExceptionUtil.sys(cause, code, args);
    }

    /**
     * 抛出系统异常
     *
     * @param cause 异常
     * @author JiaoJinxin
     */
    public static void thrSys(Throwable cause) throws SysException {
        ExceptionUtil.thrSys(cause);
    }

    /**
     * 抛出系统异常
     *
     * @param cause 异常
     * @param code  响应码code
     * @param args  响应码描述参数
     * @author JiaoJinxin
     */
    public static void thrSys(Throwable cause, String code, String... args) throws SysException {
        ExceptionUtil.thrSys(cause, code, args);
    }

    /**
     * 抛出基础异常
     *
     * @param baseException 异常
     * @author JiaoJinxin
     */
    public static void thr(BaseException baseException) throws BaseException {
        ExceptionUtil.thr(baseException);
    }

    /**
     * 静态校验对象
     *
     * @param obj    待校验的对象
     * @param code   响应码code
     * @param groups 分组
     * @author JiaoJinxin
     */
    public static void validated(Object obj, String code, Class<?>... groups) {
        ExceptionUtil.validated(obj, code, groups);
    }

    // endregion

    // region Event模块

    /**
     * 增加事件处理器
     *
     * @param eventHandler 事件处理器
     * @author JiaoJinxin
     */
    public static void addEventHandler(EventHandler<Event> eventHandler) {
        EventUtil.addEventHandler(eventHandler);
    }

    /**
     * 移除事件处理器
     *
     * @param eventHandler 事件处理器
     * @author JiaoJinxin
     */
    public static void removeEventHandler(EventHandler<Event> eventHandler) {
        EventUtil.removeEventHandler(eventHandler);
    }

    /**
     * 发布事件
     *
     * @param event 事件
     * @author JiaoJinxin
     */
    public static void publish(Event event) {
        EventUtil.publish(event);
    }

    // endregion

    // region Log模块

    /**
     * DEBUG日志
     *
     * @param logger {@link Logger}
     * @param arg    参数
     * @author JiaoJinxin
     */
    public static void debug(Logger logger, Serializable arg) {
        LogUtil.debug(logger, arg);
    }

    /**
     * DEBUG日志
     *
     * @param logger   {@link Logger}
     * @param template 字符串模板
     * @param argArray 参数
     * @author JiaoJinxin
     */
    public static void debug(Logger logger, String template, String[] argArray) {
        LogUtil.debug(logger, template, argArray);
    }

    /**
     * WARN日志
     *
     * @param logger {@link Logger}
     * @param arg    参数
     * @author JiaoJinxin
     */
    public static void warn(Logger logger, Serializable arg) {
        LogUtil.warn(logger, arg);
    }

    /**
     * WARN日志
     *
     * @param logger   {@link Logger}
     * @param template 字符串模板
     * @param argArray 参数
     * @author JiaoJinxin
     */
    public static void warn(Logger logger, String template, String[] argArray) {
        LogUtil.warn(logger, template, argArray);
    }

    /**
     * INFO日志
     *
     * @param logger {@link Logger}
     * @param arg    参数
     * @author JiaoJinxin
     */
    public static void info(Logger logger, Serializable arg) {
        LogUtil.info(logger, arg);
    }

    /**
     * INFO日志
     *
     * @param logger   {@link Logger}
     * @param template 字符串模板
     * @param argArray 参数
     * @author JiaoJinxin
     */
    public static void info(Logger logger, String template, String[] argArray) {
        LogUtil.info(logger, template, argArray);
    }

    /**
     * ERROR日志
     *
     * @param logger {@link Logger}
     * @param t      异常
     * @param arg    参数
     * @author JiaoJinxin
     */
    public static void error(Logger logger, Throwable t, Serializable arg) {
        LogUtil.error(logger, t, arg);
    }

    /**
     * ERROR日志
     *
     * @param logger   {@link Logger}
     * @param t        异常
     * @param template 字符串模板
     * @param argArray 参数
     * @author JiaoJinxin
     */
    public static void error(Logger logger, Throwable t, String template, String[] argArray) {
        LogUtil.error(logger, t, template, argArray);
    }

    // endregion

    // region Redis模块

    /**
     * 获取随机过期时间<br/>
     * 默认过期时间 + 20（不含）以内的随机数
     *
     * @return long
     * @author JiaoJinxin
     */
    public static long getExpire() {
        return RedisUtil.getExpire();
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
        return RedisUtil.getExpire(bound);
    }

    /**
     * 立即获取锁（使用默认的过期时间），并根据获取锁的结果执行相应的业务逻辑（请求失败时使用默认执行程序，无返回值逻辑）
     *
     * @param key     锁的key
     * @param success 加锁成功的业务逻辑
     * @author JiaoJinxin
     */
    public static void lock(String key, Runnable success) {
        RedisUtil.lock(key, success);
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
        RedisUtil.lock(key, success, fail);
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
        RedisUtil.lock(key, expireTime, timeUnit, success, fail);
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
        RedisUtil.lock(key, expireTime, waitTime, timeUnit, success, fail);
    }

    /**
     * 循环等待解锁（几乎无限等待），解锁成功后执行指定逻辑（无返回值逻辑）
     *
     * @param key      锁的key
     * @param runnable 执行程序（无返回值逻辑）
     * @author JiaoJinxin
     */
    public static void waitUnlockAndRun(String key, Runnable runnable) {
        RedisUtil.waitUnlockAndRun(key, runnable);
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
        RedisUtil.waitUnlockAndRun(key, runnable, waitTime, timeUnit);
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
        return RedisUtil.lock(key, success, fail);
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
        return RedisUtil.lock(key, expireTime, timeUnit, success, fail);
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
        return RedisUtil.lock(key, expireTime, waitTime, timeUnit, success, fail);
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
        return RedisUtil.waitUnlockAndRun(key, supplier);
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
        return RedisUtil.waitUnlockAndRun(key, supplier, waitTime, timeUnit);
    }

    // endregion

    // region MybatisPlus模块

    /**
     * 开启事务执行某段程序
     *
     * @param runnable 需要开启事务运行的程序
     * @author JiaoJinxin
     */
    public static void executeTransaction(Runnable runnable) {
        MpUtil.executeTransaction(runnable);
    }

    // endregion

    // region jln-spring-boot-starter模块

    /**
     * 获取客户端IP
     *
     * @param request HTTP请求
     * @author JiaoJinxin
     */
    public static String getClientIP(HttpServletRequest request) {
        String[] headerNames = {"X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};
        String ip;
        for (String header : headerNames) {
            ip = request.getHeader(header);
            if (isNotUnknown(ip)) {
                return getMultistageReverseProxyIp(ip);
            }
        }

        ip = request.getRemoteAddr();
        return getMultistageReverseProxyIp(ip);
    }

    /**
     * 从多级反向代理中获得第一个非unknown IP地址
     *
     * @param ip 获得的IP地址
     * @author JiaoJinxin
     */
    private static String getMultistageReverseProxyIp(String ip) {
        if (ip == null || ip.indexOf(COMMA) < 1) {
            return ip;
        }
        // 多级反向代理检测
        final String[] ips = ip.trim().split(COMMA);
        for (String subIp : ips) {
            if (isNotUnknown(subIp)) {
                return subIp;
            }
        }
        return ip;
    }

    /**
     * 检测HTTP客户端IP地址是否非{@code unknown}
     *
     * @param ip 被检测的字符串
     * @author JiaoJinxin
     */
    private static boolean isNotUnknown(String ip) {
        return StringUtils.hasText(ip) && !UNKNOWN.equalsIgnoreCase(ip);
    }

    // endregion
}
