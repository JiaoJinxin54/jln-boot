package top.jiaojinxin.jln.util;

import top.jiaojinxin.jln.log.JsonFormatter;
import top.jiaojinxin.jln.log.StringFormatter;

import java.util.Optional;

/**
 * 日志管理类
 *
 * @author JiaoJinxin
 */
public class LogManager {

    private LogManager() {
    }

    /**
     * JSON序列化程序
     */
    private static volatile JsonFormatter jsonFormatter;

    /**
     * 获取{@link LogManager#jsonFormatter}
     *
     * @return top.jiaojinxin.jln.log.JsonFormatter
     * @author JiaoJinxin
     */
    public static JsonFormatter getJsonFormatter() {
        if (jsonFormatter == null) {
            synchronized (LogManager.class) {
                if (jsonFormatter == null) {
                    setJsonFormatter(Object::toString);
                }
            }
        }
        return jsonFormatter;
    }

    /**
     * 设置{@link LogManager#jsonFormatter}
     *
     * @param jsonFormatter {@link LogManager#jsonFormatter}
     * @author JiaoJinxin
     */
    public static void setJsonFormatter(JsonFormatter jsonFormatter) {
        LogManager.jsonFormatter = jsonFormatter;
    }

    /**
     * 文本格式化处理程序
     */
    private static volatile StringFormatter stringFormatter;

    /**
     * 获取{@link LogManager#stringFormatter}
     *
     * @return top.jiaojinxin.jln.log.StringFormatter
     * @author JiaoJinxin
     */
    public static StringFormatter getStringFormatter() {
        if (stringFormatter == null) {
            synchronized (LogManager.class) {
                if (stringFormatter == null) {
                    setStringFormatter((strPattern, placeHolder, argArray) -> Optional.ofNullable(strPattern).orElse(""));
                }
            }
        }
        return stringFormatter;
    }

    /**
     * 设置{@link LogManager#stringFormatter}
     *
     * @param stringFormatter {@link LogManager#stringFormatter}
     * @author JiaoJinxin
     */
    public static void setStringFormatter(StringFormatter stringFormatter) {
        LogManager.stringFormatter = stringFormatter;
    }
}
