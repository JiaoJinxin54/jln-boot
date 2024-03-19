package top.jiaojinxin.common.exception;

import cn.hutool.core.text.StrFormatter;
import lombok.Getter;
import lombok.NonNull;
import top.jiaojinxin.util.I18nManager;

import java.io.Serial;

/**
 * 基础异常
 *
 * @author JiaoJinxin
 */
@Getter
public abstract class BaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 7894977553068049218L;

    /**
     * 空字符串数组
     */
    protected static final String[] EMPTY_STRING_ARRAY = new String[0];

    /**
     * 异常码code
     */
    private final String code;

    /**
     * 异常码描述
     */
    private final String msg;

    /**
     * 构造方法
     *
     * @param code  国际化码code
     * @param msg   国际化码描述
     * @param cause 具体异常
     */
    public BaseException(@NonNull String code, String msg, @NonNull Throwable cause) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    /**
     * 构造方法
     *
     * @param code  国际化码code
     * @param args  国际化码描述参数
     * @param cause 具体异常
     */
    public BaseException(@NonNull String code, @NonNull String[] args, @NonNull Throwable cause) {
        this(code, I18nManager.getMsg(code, args), cause);
    }

    /**
     * 构造方法
     *
     * @param code  国际化码code
     * @param cause 具体异常
     */
    public BaseException(@NonNull String code, @NonNull Throwable cause) {
        this(code, EMPTY_STRING_ARRAY, cause);
    }

    /**
     * 构造方法
     *
     * @param code 国际化码code
     * @param args 国际化码描述参数
     */
    public BaseException(@NonNull String code, @NonNull String[] args) {
        super();
        this.code = code;
        this.msg = I18nManager.getMsg(code, args);
    }

    /**
     * 构造方法
     *
     * @param code 国际化码code
     */
    public BaseException(@NonNull String code) {
        this(code, EMPTY_STRING_ARRAY);
    }

    @Override
    public String getMessage() {
        return StrFormatter.format("{}({})", this.code, this.msg);
    }
}
