package top.jiaojinxin.common.i18n;

import lombok.NonNull;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * 国际化描述读取程序
 *
 * @author JiaoJinxin
 */
@FunctionalInterface
public interface I18nMsgReader {

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
}
