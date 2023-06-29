package top.jiaojinxin.core.dict;

import top.jiaojinxin.core.util.CoreUtil;

/**
 * 枚举抽象
 *
 * @author JiaoJinxin
 */
public interface Dict<V> {

    /**
     * 值
     *
     * @return V
     * @author JiaoJinxin
     */
    V getValue();

    /**
     * 描述对应的多语言code
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    String getDescriptionCode();

    /**
     * 描述
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    default String getDescription() {
        return CoreUtil.getMsg(getDescriptionCode());
    }
}