package top.jiaojinxin.core.exception;

import lombok.Getter;
import lombok.NonNull;
import top.jiaojinxin.core.i18n.I18nCode;
import top.jiaojinxin.core.util.CoreUtil;

/**
 * 基础异常
 *
 * @author JiaoJinxin
 */
@Getter
public abstract class BaseException extends RuntimeException {
    private static final long serialVersionUID = 7894977553068049218L;

    protected static final String[] EMPTY_STRING_ARRAY = new String[0];

    /**
     * 异常码
     */
    private final I18nCode i18nCode;

    /**
     * 构造方法
     *
     * @param code  国际化码code
     * @param args  国际化码描述参数
     * @param cause 具体异常
     * @author JiaoJinxin
     */
    public BaseException(@NonNull String code, @NonNull String[] args, @NonNull Throwable cause) {
        super(code, cause);
        this.i18nCode = CoreUtil.getRespCode(code, args);
    }

    /**
     * 构造方法
     *
     * @param code  国际化码code
     * @param cause 具体异常
     * @author JiaoJinxin
     */
    public BaseException(@NonNull String code, @NonNull Throwable cause) {
        this(code, EMPTY_STRING_ARRAY, cause);
    }

    /**
     * 构造方法
     *
     * @param code 国际化码code
     * @param args 国际化码描述参数
     * @author JiaoJinxin
     */
    public BaseException(@NonNull String code, @NonNull String[] args) {
        super(code);
        this.i18nCode = CoreUtil.getRespCode(code, args);
    }

    /**
     * 构造方法
     *
     * @param code 国际化码code
     * @author JiaoJinxin
     */
    public BaseException(@NonNull String code) {
        this(code, EMPTY_STRING_ARRAY);
    }
}
