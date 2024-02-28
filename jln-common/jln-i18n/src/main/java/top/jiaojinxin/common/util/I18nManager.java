package top.jiaojinxin.common.util;

import lombok.NonNull;
import lombok.Setter;
import top.jiaojinxin.common.i18n.I18nCode;
import top.jiaojinxin.common.i18n.I18nCodeHolder;

/**
 * 国际化模块工具
 *
 * @author JiaoJinxin
 */
public class I18nManager {

    private I18nManager() {
    }

    /**
     * 国际化码持有者
     */
    @Setter
    private static I18nCodeHolder i18nCodeHolder;

    /**
     * 获取响应国际化码
     *
     * @param code 国际化码code
     * @param args 国际化码描述参数
     * @return top.jiaojinxin.common.i18n.I18nCode
     */
    public static I18nCode getRespCode(@NonNull String code, String... args) {
        return I18nManager.i18nCodeHolder.getRespCode(code, args);
    }

    /**
     * 获取国际化码描述
     *
     * @param code 国际化码code
     * @param args 国际化码描述参数
     * @return java.lang.String
     */
    public static String getMsg(@NonNull String code, String... args) {
        return I18nManager.i18nCodeHolder.getMsg(code, args);
    }
}
