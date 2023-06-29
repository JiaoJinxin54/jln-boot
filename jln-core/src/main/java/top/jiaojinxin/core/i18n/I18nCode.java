package top.jiaojinxin.core.i18n;

import java.util.Locale;

/**
 * 国际化码
 *
 * @author JiaoJinxin
 */
public interface I18nCode {

    /**
     * 区域
     *
     * @return java.util.Locale
     * @author JiaoJinxin
     */
    Locale locale();

    /**
     * 国际化码Code
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    String code();

    /**
     * 国际化码描述
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    String msg();
}
