package top.jiaojinxin.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.validation.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import top.jiaojinxin.common.exception.BizException;
import top.jiaojinxin.common.util.I18nManager;
import top.jiaojinxin.common.util.PropertiesManager;

import java.util.Optional;
import java.util.Set;

/**
 * 验证工具类
 *
 * @author JiaoJinxin
 */
public class ValidatedUtil {
    private ValidatedUtil() {
    }

    /**
     * 参数验证（使用指定code）
     *
     * @param obj    待验证的参数实体对象
     * @param groups 验证分组
     */
    public static void validated(Object obj, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolationSet = ValidationUtil.validate(obj, groups);
        if (CollUtil.isEmpty(constraintViolationSet)) {
            return;
        }
        String[] args = constraintViolationSet.stream()
                .map(ValidatedUtil::getMsgFromConstraintViolation)
                .filter(StrUtil::isBlank).toArray(String[]::new);
        if (ArrayUtil.isNotEmpty(args)) {
            throw new BizException(PropertiesManager.getParamError(), args);
        }
    }

    /**
     * 从{@link ConstraintViolation}中提取响应码并转换为响应码描述
     *
     * @param v {@link ConstraintViolation}
     * @return java.lang.String
     */
    private static String getMsgFromConstraintViolation(ConstraintViolation<Object> v) {
        String message = Optional.ofNullable(v.getMessageTemplate()).orElse(v.getMessage());
        return StrUtil.isBlank(message) ? message : I18nManager.getMsg(message);
    }
}
