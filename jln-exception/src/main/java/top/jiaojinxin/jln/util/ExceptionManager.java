package top.jiaojinxin.jln.util;

import jakarta.validation.Validator;

/**
 * 异常管理类
 *
 * @author JiaoJinxin
 */
public class ExceptionManager {
    private ExceptionManager() {
    }

    /**
     * 参数验证器
     */
    private static Validator validator;

    /**
     * 获取{@link ExceptionManager#validator}
     *
     * @return jakarta.validation.Validator
     * @author JiaoJinxin
     */
    public static Validator getValidator() {
        return validator;
    }

    /**
     * 设置{@link ExceptionManager#validator}
     *
     * @param validator {@link ExceptionManager#validator}
     * @author JiaoJinxin
     */
    public static void setValidator(Validator validator) {
        ExceptionManager.validator = validator;
    }
}
