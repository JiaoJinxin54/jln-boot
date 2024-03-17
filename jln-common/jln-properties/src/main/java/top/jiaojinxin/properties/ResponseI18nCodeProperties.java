package top.jiaojinxin.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serial;
import java.io.Serializable;

/**
 * 响应国际化码配置
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
@ConfigurationProperties(prefix = ResponseI18nCodeProperties.PREFIX)
public class ResponseI18nCodeProperties implements Serializable {
    @Serial
    private static final long serialVersionUID = -1679772346046219062L;

    /**
     * 前缀
     */
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
     * 非Servlet环境，无法获取ServletRequestAttributes，默认：respCode.servletRequestAttributesNotFound
     */
    private String servletRequestAttributesNotFound = "respCode.servletRequestAttributesNotFound";

    /**
     * 参数异常，默认：respCode.paramError
     */
    private String paramError = "respCode.paramError";

    /**
     * 接口地址无效，默认：respCode.urlInvalid
     */
    private String urlInvalid = "respCode.urlInvalid";

    /**
     * 非法请求，默认：respCode.illegalRequest
     */
    private String illegalRequest = "respCode.illegalRequest";
}