package top.jiaojinxin.jln.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>审计日志注解</p>
 * <p>可通过页面进行查看审计日志</p>
 *
 * @author JiaoJinxin
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditLog {

    /**
     * 描述
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    String describe();
}
