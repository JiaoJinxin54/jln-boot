package top.jiaojinxin.jln.exception;

import top.jiaojinxin.jln.util.RespCodeManager;

import java.io.Serial;

/**
 * 系统异常
 *
 * @author JiaoJinxin
 */
public class SysException extends BaseException {

    @Serial
    private static final long serialVersionUID = 1549844593206959341L;

    /**
     * 构造方法
     *
     * @param code 响应码code
     * @param args 响应码描述参数
     * @author JiaoJinxin
     */
    public SysException(String code, String[] args, Throwable cause) {
        super(getDefaultIfNull(code, RespCodeManager.getRespCodeProperties().getFailSys()), args, cause);
    }
}
