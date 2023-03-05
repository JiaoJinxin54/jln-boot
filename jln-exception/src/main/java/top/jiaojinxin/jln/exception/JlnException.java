package top.jiaojinxin.jln.exception;

import top.jiaojinxin.jln.util.RespCodeManager;

import java.io.Serial;

/**
 * 框架异常
 *
 * @author JiaoJinxin
 */
public class JlnException extends BaseException {

    @Serial
    private static final long serialVersionUID = -5768654026701465899L;

    /**
     * 构造方法
     *
     * @param code 响应码code
     * @param args 响应码描述参数
     * @author JiaoJinxin
     */
    public JlnException(String code, String[] args) {
        super(getDefaultIfNull(code, RespCodeManager.getRespCodeProperties().getFailJln()), args);
    }
}
