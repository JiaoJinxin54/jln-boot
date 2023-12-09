package top.jiaojinxin.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * 统一响应国际化码配置
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
@ConfigurationProperties(prefix = ResponseI18nCodeProperties.PREFIX)
public class ResponseI18nCodeProperties implements Serializable {
    private static final long serialVersionUID = -5716874899492609994L;

    public static final String PREFIX = "jln.response.i18n-code";

    /**
     * 成功响应国际化码code，默认：respCode.success
     */
    private String success = "respCode.success";

    /**
     * 失败响应国际化码code，默认：respCode.fail
     */
    private String fail = "respCode.fail";

    /**
     * 系统异常国际化码code，默认：respCode.failSys
     */
    private String failSys = "respCode.failSys";

    /**
     * 业务异常国际化码code，默认：respCode.failBiz
     */
    private String failBiz = "respCode.failBiz";

    /**
     * 无法获取国际化码描述时的默认值，默认：""
     */
    private String defaultMsg = "";
}
