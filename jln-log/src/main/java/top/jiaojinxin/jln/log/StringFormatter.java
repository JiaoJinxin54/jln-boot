package top.jiaojinxin.jln.log;

/**
 * 文本格式化处理程序
 *
 * @author JiaoJinxin
 */
@FunctionalInterface
public interface StringFormatter {

    /**
     * 格式化文本
     *
     * @param strPattern  字符串模版
     * @param placeHolder 占位符
     * @param argArray    参数列表
     * @return java.lang.String
     * @author JiaoJinxin
     */
    String format(String strPattern, String placeHolder, Object... argArray);

    /**
     * 默认占位符
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    default String defaultPlaceHolder() {
        return null;
    }

    /**
     * 格式化文本，使用默认的占位符
     *
     * @param strPattern 字符串模版
     * @param argArray   参数列表
     * @return java.lang.String
     * @author JiaoJinxin
     */
    default String format(String strPattern, Object... argArray) {
        return format(strPattern, defaultPlaceHolder(), argArray);
    }
}
