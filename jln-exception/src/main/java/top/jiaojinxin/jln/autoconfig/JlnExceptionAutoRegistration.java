package top.jiaojinxin.jln.autoconfig;

import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import top.jiaojinxin.jln.util.ExceptionManager;

/**
 * 异常模块自动注册
 *
 * @author JiaoJinxin
 */
public class JlnExceptionAutoRegistration {

    @Autowired
    public void setValidator(Validator validator) {
        ExceptionManager.setValidator(validator);
    }
}
