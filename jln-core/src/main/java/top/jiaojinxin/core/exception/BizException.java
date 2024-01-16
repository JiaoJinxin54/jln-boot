package top.jiaojinxin.core.exception;

import lombok.NonNull;
import top.jiaojinxin.core.util.CoreUtil;

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
     * @param code 国际化码code
     * @param args 国际化码描述参数
     */
    public BizException(@NonNull String code, @NonNull String[] args) {
        super(code, args);
    }

    /**
     * 构造方法
     *
     * @param code 国际化码code
     */
    public BizException(@NonNull String code) {
        this(code, EMPTY_STRING_ARRAY);
    }

    /**
     * 构造方法
     */
    public BizException() {
        this(CoreUtil.getFailBiz());
    }
}
