package top.jiaojinxin.common.model.dict;

import top.jiaojinxin.util.I18nManager;

/**
 * 枚举抽象
 *
 * @param <V> 字典值泛型
 * @author JiaoJinxin
 */
public interface Dict<V> {

    /**
     * 值
     *
     * @return V
     */
    V getValue();

    /**
     * 描述对应的多语言code
     *
     * @return java.lang.String
     */
    String getDescriptionCode();

    /**
     * 描述
     *
     * @return java.lang.String
     */
    default String getDescription() {
        return I18nManager.getMsg(getDescriptionCode());
    }
}