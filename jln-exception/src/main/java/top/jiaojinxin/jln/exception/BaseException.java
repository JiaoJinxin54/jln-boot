package top.jiaojinxin.jln.exception;

import org.springframework.util.StringUtils;

import java.io.Serial;

/**
 * 基础异常
 *
 * @author JiaoJinxin
 */
public abstract class BaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 7894977553068049218L;

    /**
     * 异常码
     */
    private final String code;

    /**
     * 异常提示信息参数
     */
    private final String[] args;

    /**
     * 构造方法
     *
     * @param code 响应码code
     * @param args 响应码描述参数
     * @author JiaoJinxin
     */
    public BaseException(String code, String[] args) {
        super(code);
        this.code = code;
        this.args = args;
    }

    /**
     * 构造方法
     *
     * @param code  响应码code
     * @param args  响应码描述参数
     * @param cause 具体异常
     * @author JiaoJinxin
     */
    public BaseException(String code, String[] args, Throwable cause) {
        super(code, cause);
        this.code = code;
        this.args = args;
    }

    /**
     * 获取{@link BaseException#code}
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 获取{@link BaseException#args}
     *
     * @return java.lang.String[]
     * @author JiaoJinxin
     */
    public String[] getArgs() {
        return this.args;
    }

    /**
     * 当响应码code不存在时，取默认的响应码code
     *
     * @param code        响应码code
     * @param defaultCode 默认响应码code
     * @return java.lang.String
     * @author JiaoJinxin
     */
    protected static String getDefaultIfNull(String code, String defaultCode) {
        return StringUtils.hasText(code) ? code : defaultCode;
    }
}
