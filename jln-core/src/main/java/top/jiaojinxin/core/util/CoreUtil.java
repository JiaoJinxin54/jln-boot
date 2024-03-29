package top.jiaojinxin.core.util;

import lombok.NonNull;
import lombok.Setter;
import org.springframework.context.i18n.LocaleContextHolder;
import top.jiaojinxin.core.i18n.I18nCode;
import top.jiaojinxin.core.i18n.I18nCodeHolder;
import top.jiaojinxin.core.properties.ResponseI18nCodeProperties;

import java.util.Locale;

/**
 * 核心模块工具
 *
 * @author JiaoJinxin
 */
public class CoreUtil {

    private CoreUtil() {
    }

    /**
     * 统一响应国际化码配置
     */
    @Setter
    private static ResponseI18nCodeProperties responseI18nCodeProperties;

    /**
     * 国际化码持有者
     */
    @Setter
    private static I18nCodeHolder i18nCodeHolder;

    /**
     * 获取默认统一响应国际化码描述
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    public static String getDefaultMsg() {
        return CoreUtil.responseI18nCodeProperties.getDefaultMsg();
    }

    /**
     * 获取响应国际化码code（成功）
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    public static String getSuccess() {
        return CoreUtil.responseI18nCodeProperties.getSuccess();
    }

    /**
     * 获取响应国际化码code（失败）
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    public static String getFail() {
        return CoreUtil.responseI18nCodeProperties.getFail();
    }

    /**
     * 获取响应国际化码code（系统异常）
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    public static String getFailSys() {
        return CoreUtil.responseI18nCodeProperties.getFailSys();
    }

    /**
     * 获取响应国际化码code（业务异常）
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    public static String getFailBiz() {
        return CoreUtil.responseI18nCodeProperties.getFailBiz();
    }

    /**
     * 获取响应国际化码（成功）
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    public static I18nCode getSuccessRespCode() {
        return getRespCode(getSuccess());
    }

    /**
     * 获取响应国际化码（失败）
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    public static I18nCode getFailRespCode() {
        return getRespCode(getFail());
    }

    /**
     * 获取响应国际化码
     *
     * @param code 国际化码code
     * @param args 国际化码描述参数
     * @return top.jiaojinxin.jln.respcode.RespCode
     * @author JiaoJinxin
     */
    public static I18nCode getRespCode(@NonNull String code, String... args) {
        return getRespCode(getDefaultLocale(), code, args);
    }

    /**
     * 获取国际化码
     *
     * @param locale 区域
     * @param code   国际化码code
     * @param args   国际化码描述参数
     * @return top.jiaojinxin.jln.respcode.RespCode
     * @author JiaoJinxin
     */
    public static I18nCode getRespCode(@NonNull Locale locale, @NonNull String code, String... args) {
        return CoreUtil.i18nCodeHolder.getRespCode(locale, code, getDefaultMsg(), args);
    }

    /**
     * 获取国际化码描述
     *
     * @param code 国际化码code
     * @param args 国际化码描述参数
     * @return java.lang.String
     * @author JiaoJinxin
     */
    public static String getMsg(@NonNull String code, String... args) {
        return getMsg(getDefaultLocale(), code, args);
    }

    /**
     * 获取国际化码描述
     *
     * @param locale 区域
     * @param code   国际化码code
     * @param args   国际化码描述参数
     * @return java.lang.String
     * @author JiaoJinxin
     */
    public static String getMsg(@NonNull Locale locale, @NonNull String code, String... args) {
        return CoreUtil.i18nCodeHolder.getMsg(locale, code, getDefaultMsg(), args);
    }

    /**
     * 获取默认{@link Locale}
     *
     * @return java.util.Locale
     * @author JiaoJinxin
     */
    private static Locale getDefaultLocale() {
        return LocaleContextHolder.getLocale();
    }
}
