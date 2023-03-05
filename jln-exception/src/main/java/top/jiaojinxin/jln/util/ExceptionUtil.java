package top.jiaojinxin.jln.util;

import jakarta.validation.ConstraintViolation;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import top.jiaojinxin.jln.exception.BaseException;
import top.jiaojinxin.jln.exception.BizException;
import top.jiaojinxin.jln.exception.JlnException;
import top.jiaojinxin.jln.exception.SysException;

import java.util.Optional;
import java.util.Set;

/**
 * 异常工厂类
 *
 * @author JiaoJinxin
 */
public class ExceptionUtil {

    private static final String EMPTY_STRING = "";
    private static final String SEPARATOR_STRING = "|";
    private static final String JSON_PREFIX = "{";
    private static final String JSON_SUFFIX = "}";

    private ExceptionUtil() {
    }

    /**
     * 构建业务异常（使用默认code）
     *
     * @return top.jiaojinxin.jln.exception.BizException
     * @author JiaoJinxin
     */
    public static BizException biz() {
        return biz(null);
    }

    /**
     * 构建业务异常（使用自定义code）
     *
     * @param code 响应码code
     * @param args 响应码描述参数
     * @return top.jiaojinxin.jln.exception.BizException
     * @author JiaoJinxin
     */
    public static BizException biz(String code, String... args) {
        return new BizException(code, args);
    }

    /**
     * 抛出业务异常（使用默认code）
     *
     * @author JiaoJinxin
     */
    public static void thrBiz() throws BizException {
        thr(biz());
    }

    /**
     * 抛出业务异常（使用自定义code）
     *
     * @param code 响应码code
     * @param args 响应码描述参数
     * @author JiaoJinxin
     */
    public static void thrBiz(String code, String... args) throws BizException {
        thr(biz(code, args));
    }

    /**
     * 构建框架异常（使用默认code）
     *
     * @return top.jiaojinxin.jln.exception.JlnException
     * @author JiaoJinxin
     */
    public static JlnException jln() {
        return jln(null);
    }

    /**
     * 构建框架异常（使用自定义code）
     *
     * @param code 响应码code
     * @param args 响应码描述参数
     * @return top.jiaojinxin.jln.exception.JlnException
     * @author JiaoJinxin
     */
    public static JlnException jln(String code, String... args) {
        return new JlnException(code, args);
    }

    /**
     * 抛出框架异常（使用默认code）
     *
     * @author JiaoJinxin
     */
    public static void thrJln() throws JlnException {
        thr(jln());
    }

    /**
     * 抛出框架异常（使用自定义code）
     *
     * @param code 响应码code
     * @param args 响应码描述参数
     * @author JiaoJinxin
     */
    public static void thrJln(String code, String... args) throws JlnException {
        thr(jln(code, args));
    }

    /**
     * 构建系统异常（使用默认code）
     *
     * @return top.jiaojinxin.jln.exception.SysException
     * @author JiaoJinxin
     * @author JiaoJinxin
     */
    public static SysException sys(Throwable cause) {
        return sys(cause, null);
    }

    /**
     * 构建系统异常（使用自定义code）
     *
     * @param cause 具体异常
     * @param code  响应码code
     * @param args  响应码描述参数
     * @return top.jiaojinxin.jln.exception.SysException
     * @author JiaoJinxin
     * @author JiaoJinxin
     */
    public static SysException sys(Throwable cause, String code, String... args) {
        return new SysException(code, args, cause);
    }

    /**
     * 抛出系统异常（使用默认code）
     *
     * @author JiaoJinxin
     */
    public static void thrSys(Throwable cause) throws SysException {
        thr(sys(cause));
    }

    /**
     * 抛出系统异常（使用自定义code）
     *
     * @param cause 具体异常
     * @param code  响应码code
     * @param args  响应码描述参数
     * @author JiaoJinxin
     */
    public static void thrSys(Throwable cause, String code, String... args) throws SysException {
        thr(sys(cause, code, args));
    }

    /**
     * 抛出基础异常
     *
     * @param baseException 异常
     * @author JiaoJinxin
     */
    public static void thr(BaseException baseException) throws BaseException {
        throw baseException;
    }

    /**
     * 参数验证（使用指定code）
     *
     * @param obj    待验证的参数实体对象
     * @param code   响应码code
     * @param groups 验证分组
     * @author JiaoJinxin
     */
    public static void validated(Object obj, String code, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolationSet = ExceptionManager.getValidator().validate(obj, groups);
        if (CollectionUtils.isEmpty(constraintViolationSet)) {
            return;
        }
        String arg = constraintViolationSet.parallelStream()
                .map(v -> {
                    String message = Optional.ofNullable(v.getMessageTemplate()).orElse(v.getMessage());
                    if (!StringUtils.hasText(message)) {
                        return message;
                    }
                    String validatedMsg;
                    if (message.startsWith(JSON_PREFIX) && message.endsWith(JSON_SUFFIX) && message.length() > 2) {
                        String validatedCode = message.substring(1, message.length() - 1);
                        validatedMsg = RespCodeUtil.getRespCode(validatedCode).msg();
                    } else {
                        validatedMsg = message;
                    }
                    return StringUtils.hasText(validatedMsg) ? validatedMsg : v.getMessage();
                })
                .filter(StringUtils::hasText)
                .reduce((s1, s2) -> String.join(SEPARATOR_STRING, s1, s2))
                .orElse(EMPTY_STRING);
        if (StringUtils.hasText(arg)) {
            thrBiz(Optional.ofNullable(code).orElse(RespCodeManager.getRespCodeProperties().getFailBiz()), arg);
        }
    }
}
