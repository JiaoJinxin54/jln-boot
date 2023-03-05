package top.jiaojinxin.jln.respcode;

import top.jiaojinxin.jln.util.RespCodeManager;

import java.util.Locale;

/**
 * {@link RespCode}持有者
 *
 * @author JiaoJinxin
 */
@FunctionalInterface
public interface RespCodeHolder {

    /**
     * 获取响应码描述
     *
     * @param code         响应码code
     * @param args         响应码描述参数
     * @param defaultValue 默认的响应码描述
     * @param locale       区域
     * @return java.lang.String
     * @author JiaoJinxin
     */
    String getMsg(String code, String[] args, String defaultValue, Locale locale);

    /**
     * 获取响应码
     *
     * @param code         响应码code
     * @param args         响应码描述参数
     * @param defaultValue 默认的响应码描述
     * @return top.jiaojinxin.jln.respcode.RespCode
     * @author JiaoJinxin
     */
    default RespCode getRespCode(String code, String[] args, String defaultValue) {
        return new DefRespCodeImpl(code, getMsg(code, args, defaultValue, RespCodeManager.getLocaleHolder().getLocale()));
    }

    /**
     * 默认的响应码实现
     *
     * @author JiaoJinxin
     */
    record DefRespCodeImpl(String code, String msg) implements RespCode {
    }
}
