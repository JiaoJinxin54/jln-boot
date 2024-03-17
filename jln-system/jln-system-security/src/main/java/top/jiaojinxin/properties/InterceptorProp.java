package top.jiaojinxin.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 拦截器配置
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InterceptorProp implements Serializable {
    @Serial
    private static final long serialVersionUID = 478374825340108984L;

    /**
     * 是否开启，默认：否
     */
    private boolean enable = false;

    /**
     * 拦截的请求，默认：所有请求
     */
    private String[] addPath = {"/**"};

    /**
     * 忽略拦截的请求，默认：空
     */
    private String[] excludePath = {};
}
