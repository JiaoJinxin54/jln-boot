package top.jiaojinxin.jln.util;

import org.springframework.context.i18n.LocaleContextHolder;
import top.jiaojinxin.jln.properties.JlnRespCodeProperties;
import top.jiaojinxin.jln.respcode.LocaleHolder;
import top.jiaojinxin.jln.respcode.RespCode;
import top.jiaojinxin.jln.respcode.RespCodeHolder;

import java.util.Locale;

/**
 * 响应码管理类
 *
 * @author JiaoJinxin
 */
public class RespCodeManager {

    private static final String EMPTY = "";

    private RespCodeManager() {
    }

    /**
     * 响应码相关配置
     */
    private static JlnRespCodeProperties respCodeProperties;

    /**
     * 获取{@link JlnRespCodeProperties}
     *
     * @return top.jiaojinxin.jln.properties.JlnRespCodeProperties
     * @author JiaoJinxin
     */
    public static JlnRespCodeProperties getRespCodeProperties() {
        return respCodeProperties;
    }

    /**
     * 设置{@link JlnRespCodeProperties}
     *
     * @param respCodeProperties {@link JlnRespCodeProperties}
     * @author JiaoJinxin
     */
    public static void setRespCodeProperties(JlnRespCodeProperties respCodeProperties) {
        RespCodeManager.respCodeProperties = respCodeProperties;
    }

    /**
     * {@link Locale}持有者
     */
    private static volatile LocaleHolder localeHolder;

    /**
     * 获取{@link LocaleHolder}
     *
     * @return top.jiaojinxin.jln.respcode.LocaleHolder
     * @author JiaoJinxin
     */
    public static LocaleHolder getLocaleHolder() {
        if (localeHolder == null) {
            synchronized (RespCodeManager.class) {
                if (localeHolder == null) {
                    setLocaleHolder(LocaleContextHolder::getLocale);
                }
            }
        }
        return localeHolder;
    }

    /**
     * 设置{@link LocaleHolder}
     *
     * @param localeHolder {@link LocaleHolder}
     * @author JiaoJinxin
     */
    public static void setLocaleHolder(LocaleHolder localeHolder) {
        RespCodeManager.localeHolder = localeHolder;
    }

    /**
     * {@link RespCode}持有者
     */
    private static volatile RespCodeHolder respCodeHolder;

    /**
     * 获取{@link RespCodeHolder}
     *
     * @return top.jiaojinxin.jln.respcode.RespCodeHolder
     * @author JiaoJinxin
     */
    public static RespCodeHolder getRespCodeHolder() {
        if (respCodeHolder == null) {
            synchronized (RespCodeManager.class) {
                if (respCodeHolder == null) {
                    setRespCodeHolder((code, args, defaultValue, locale) -> EMPTY);
                }
            }
        }
        return respCodeHolder;
    }

    /**
     * 设置{@link RespCodeHolder}
     *
     * @param respCodeHolder {@link RespCodeHolder}
     * @author JiaoJinxin
     */
    public static void setRespCodeHolder(RespCodeHolder respCodeHolder) {
        RespCodeManager.respCodeHolder = respCodeHolder;
    }
}
