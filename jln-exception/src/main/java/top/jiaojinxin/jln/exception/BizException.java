package top.jiaojinxin.jln.exception;

import top.jiaojinxin.jln.util.RespCodeManager;

import java.io.Serial;

/**
 * 业务异常
 *
 * @author JiaoJinxin
 */
public class BizException extends BaseException {

    @Serial
    private static final long serialVersionUID = -3122375441754321982L;

    /**
     * 构造方法
     *
     * @param code 响应码code
     * @param args 响应码描述参数
     * @author JiaoJinxin
     */
    public BizException(String code, String[] args) {
        super(getDefaultIfNull(code, RespCodeManager.getRespCodeProperties().getFailBiz()), args);
    }
}
