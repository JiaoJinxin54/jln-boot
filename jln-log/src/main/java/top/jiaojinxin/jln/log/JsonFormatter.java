package top.jiaojinxin.jln.log;

/**
 * JSON处理程序
 *
 * @author JiaoJinxin
 */
@FunctionalInterface
public interface JsonFormatter {

    /**
     * 将对象转换为JSON字符串
     *
     * @param arg 待转换的对象
     * @return java.lang.String
     * @author JiaoJinxin
     */
    String toStr(Object arg);
}
