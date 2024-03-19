package top.jiaojinxin.util;

import lombok.NonNull;
import lombok.Setter;
import top.jiaojinxin.common.i18n.I18nMsgReader;

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
    private static I18nMsgReader i18nMsgReader;

    /**
     * 获取国际化码描述
     *
     * @param code 国际化码code
     * @param args 国际化码描述参数
     * @return java.lang.String
     */
    public static String getMsg(@NonNull String code, String... args) {
        return I18nManager.i18nMsgReader.getMsg(code, args);
    }
}
