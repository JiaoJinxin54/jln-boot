package top.jiaojinxin.core.i18n;

import lombok.NonNull;
import org.springframework.context.i18n.LocaleContextHolder;

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
     * @param locale 区域
     * @param code   国际化码code
     * @param args   国际化码描述参数
     * @return java.lang.String
     */
    String getMsg(@NonNull Locale locale, @NonNull String code, String... args);

    /**
     * 获取国际化码描述
     *
     * @param code 国际化码code
     * @param args 国际化码描述参数
     * @return java.lang.String
     */
    default String getMsg(@NonNull String code, String... args) {
        return getMsg(LocaleContextHolder.getLocale(), code, args);
    }

    /**
     * 获取国际化码
     *
     * @param code 国际化码code
     * @param args 国际化码描述参数
     * @return top.jiaojinxin.core.i18n.I18nCode
     */
    default I18nCode getRespCode(@NonNull String code, String... args) {
        return new DefI18nCodeImpl(code, getMsg(code, args));
    }

    /**
     * 默认的国际化码实现
     *
     * @param code 国际化码code
     * @param msg  国际化码描述
     */
    record DefI18nCodeImpl(String code, String msg) implements I18nCode {
    }
}
