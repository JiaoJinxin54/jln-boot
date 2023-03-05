package top.jiaojinxin.jln.respcode;

import java.util.Locale;

/**
 * {@link Locale}持有者
 *
 * @author JiaoJinxin
 */
public interface LocaleHolder {

    /**
     * 获取地理、政治或文化区域
     *
     * @return java.util.Locale
     * @author JiaoJinxin
     */
    Locale getLocale();
}
