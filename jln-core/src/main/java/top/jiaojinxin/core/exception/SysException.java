package top.jiaojinxin.core.exception;

import lombok.NonNull;
import top.jiaojinxin.core.util.CoreUtil;

/**
 * 系统异常
 *
 * @author JiaoJinxin
 */
public class SysException extends BaseException {
    private static final long serialVersionUID = 1549844593206959341L;

    /**
     * 构造方法
     *
     * @param code  国际化码code
     * @param args  国际化码描述参数
     * @param cause 具体异常
     * @author JiaoJinxin
     */
    public SysException(@NonNull String code, @NonNull String[] args, @NonNull Throwable cause) {
        super(code, args, cause);
    }

    /**
     * 构造方法
     *
     * @param code  国际化码code
     * @param cause 具体异常
     * @author JiaoJinxin
     */
    public SysException(@NonNull String code, @NonNull Throwable cause) {
        this(code, EMPTY_STRING_ARRAY, cause);
    }

    /**
     * 构造方法
     *
     * @param cause 具体异常
     * @author JiaoJinxin
     */
    public SysException(@NonNull Throwable cause) {
        this(CoreUtil.getFailSys(), cause);
    }
}
