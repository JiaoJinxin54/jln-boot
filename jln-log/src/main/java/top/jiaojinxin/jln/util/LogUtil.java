package top.jiaojinxin.jln.util;

import org.slf4j.Logger;

import java.io.Serializable;

/**
 * 日志工具类
 *
 * @author JiaoJinxin
 */
public class LogUtil {
    private LogUtil() {
    }

    /**
     * 空字符串数组
     */
    private static final String[] EMPTY_STR_ARR = new String[0];

    /**
     * DEBUG日志
     *
     * @param logger {@link Logger}
     * @param arg    参数
     * @author JiaoJinxin
     */
    public static void debug(Logger logger, Serializable arg) {
        if (arg instanceof String str) {
            debug(logger, str, EMPTY_STR_ARR);
        } else {
            debug(logger, toJsonStr(arg), EMPTY_STR_ARR);
        }
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
        if (logger.isDebugEnabled()) {
            logger.debug(format(template, argArray));
        }
    }

    /**
     * WARN日志
     *
     * @param logger {@link Logger}
     * @param arg    参数
     * @author JiaoJinxin
     */
    public static void warn(Logger logger, Serializable arg) {
        if (arg instanceof String str) {
            warn(logger, str, EMPTY_STR_ARR);
        } else {
            warn(logger, toJsonStr(arg), EMPTY_STR_ARR);
        }
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
        if (logger.isWarnEnabled()) {
            logger.warn(format(template, argArray));
        }
    }

    /**
     * INFO日志
     *
     * @param logger {@link Logger}
     * @param arg    参数
     * @author JiaoJinxin
     */
    public static void info(Logger logger, Serializable arg) {
        if (arg instanceof String str) {
            info(logger, str, EMPTY_STR_ARR);
        } else {
            info(logger, toJsonStr(arg), EMPTY_STR_ARR);
        }
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
        if (logger.isInfoEnabled()) {
            logger.info(format(template, argArray));
        }
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
        if (arg instanceof String str) {
            error(logger, t, str, EMPTY_STR_ARR);
        } else {
            error(logger, t, toJsonStr(arg), EMPTY_STR_ARR);
        }
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
        if (logger.isErrorEnabled()) {
            logger.error(format(template, argArray), t);
        }
    }

    /**
     * 将参数转换为JSON字符串
     *
     * @param arg 参数
     * @return java.lang.String
     * @author JiaoJinxin
     */
    private static String toJsonStr(Serializable arg) {
        return LogManager.getJsonFormatter().toStr(arg);
    }

    /**
     * 文本格式化
     *
     * @param template 文本格式化模板
     * @param argArray 文本格式化参数
     * @return java.lang.String
     * @author JiaoJinxin
     */
    private static String format(String template, String[] argArray) {
        return LogManager.getStringFormatter().format(template, (Object[]) argArray);
    }
}
