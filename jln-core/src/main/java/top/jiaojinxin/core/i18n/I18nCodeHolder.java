package top.jiaojinxin.core.i18n;

import lombok.NonNull;

import java.util.Locale;

/**
 * {@link I18nCode}持有者
 *
 * @author JiaoJinxin
 */
@FunctionalInterface
public interface I18nCodeHolder {

    /**
     * 获取国际化码描述
     *
     * @param locale     区域
     * @param code       国际化码code
     * @param defaultMsg 默认的国际化码描述
     * @param args       国际化码描述参数
     * @return java.lang.String
     * @author JiaoJinxin
     */
    String getMsg(@NonNull Locale locale, @NonNull String code, @NonNull String defaultMsg, String... args);

    /**
     * 获取国际化码
     *
     * @param locale     区域
     * @param code       国际化码code
     * @param defaultMsg 默认的国际化码描述
     * @param args       国际化码描述参数
     * @return top.jiaojinxin.core.code.RespCode
     * @author JiaoJinxin
     */
    default I18nCode getRespCode(@NonNull Locale locale, @NonNull String code, @NonNull String defaultMsg, String... args) {
        return new DefI18nCodeImpl(locale, code, getMsg(locale, code, defaultMsg, args));
    }

    /**
     * 默认的国际化码实现
     *
     * @author JiaoJinxin
     */
    record DefI18nCodeImpl(Locale locale, String code, String msg) implements I18nCode {
    }
}
