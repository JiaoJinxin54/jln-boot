package top.jiaojinxin.jln.util;

import top.jiaojinxin.jln.respcode.RespCode;

import static top.jiaojinxin.jln.util.RespCodeManager.getRespCodeProperties;
import static top.jiaojinxin.jln.util.RespCodeManager.getRespCodeHolder;

/**
 * 响应码工具类
 *
 * @author JiaoJinxin
 */
public class RespCodeUtil {

    private RespCodeUtil() {
    }

    /**
     * 获取响应码（请求执行成功）
     *
     * @return top.jiaojinxin.jln.respcode.RespCode
     * @author JiaoJinxin
     */
    public static RespCode success() {
        return getRespCode(getRespCodeProperties().getSuccess());
    }

    /**
     * 获取响应码（请求执行失败）
     *
     * @return top.jiaojinxin.jln.respcode.RespCode
     * @author JiaoJinxin
     */
    public static RespCode fail() {
        return getRespCode(getRespCodeProperties().getFail());
    }

    /**
     * 获取响应码（指定的响应码）
     *
     * @param code 响应码code
     * @param args 响应码描述参数
     * @return top.jiaojinxin.jln.respcode.RespCode
     * @author JiaoJinxin
     */
    public static RespCode getRespCode(String code, String... args) {
        return getRespCodeHolder().getRespCode(code, args, getRespCodeProperties().getDefaultMsg());
    }
}
