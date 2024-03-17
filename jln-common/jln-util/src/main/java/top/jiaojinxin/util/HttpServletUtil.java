package top.jiaojinxin.util;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.jiaojinxin.common.exception.BizException;
import top.jiaojinxin.properties.RequestHeaderProperties;

import java.util.function.Function;

/**
 * Servlet工具类
 *
 * @author JiaoJinxin
 */
public class HttpServletUtil {

    /**
     * 请求属性名称（Request Attribute Name）：加密参数内容
     */
    private static final String ENCRYPTED_PARAMETER_CONTENT_ATTRIBUTE_NAME = "ATTRIBUTE_NAME_ENCRYPTED_PARAMETER_CONTENT";

    private HttpServletUtil() {
    }

    /**
     * 获取{@link ServletRequestAttributes}
     *
     * @return org.springframework.web.context.request.ServletRequestAttributes
     */
    private static ServletRequestAttributes getServletRequestAttributes() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
            return servletRequestAttributes;
        }
        throw new BizException(PropertiesManager.getServletRequestAttributesNotFound());
    }

    /**
     * 获取{@link HttpServletRequest}
     *
     * @return jakarta.servlet.http.HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        return getServletRequestAttributes().getRequest();
    }

    /**
     * 请求头（获取）：签名值
     *
     * @return java.lang.String
     */
    public static String getRequestHeaderSign() {
        return getRequestHeader(RequestHeaderProperties::getSign);
    }

    /**
     * 请求头（获取）：客户端标识
     *
     * @return java.lang.String
     */
    public static String getRequestHeaderClientCode() {
        return getRequestHeader(RequestHeaderProperties::getClient);
    }

    /**
     * 请求头（获取）：时间戳
     *
     * @return java.lang.String
     */
    public static String getRequestHeaderTimestamp() {
        return getRequestHeader(RequestHeaderProperties::getTimestamp);
    }

    /**
     * 请求头（获取）：唯一键
     *
     * @return java.lang.String
     */
    public static String getRequestHeaderUid() {
        return getRequestHeader(RequestHeaderProperties::getUid);
    }

    /**
     * 请求头（获取）：加密算法盐值
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    public static String getRequestHeaderSalt() {
        return getRequestHeader(RequestHeaderProperties::getSalt);
    }

    /**
     * 请求头（获取）：加密算法
     *
     * @return java.lang.String
     */
    public static String getRequestHeaderAlgorithm() {
        return getRequestHeader(RequestHeaderProperties::getAlgorithm);
    }

    /**
     * 请求头（获取）：链路追踪标识
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    public static String getRequestHeaderTrace() {
        return getRequestHeader(RequestHeaderProperties::getTrace);
    }

    /**
     * 获取header内容
     *
     * @param keyFunction header名称获取逻辑
     * @return java.lang.String
     * @author JiaoJinxin
     */
    private static String getRequestHeader(Function<RequestHeaderProperties, String> keyFunction) {
        String headerKeyPrefix = PropertiesManager.getRequestHeaderPrefix();
        String headerKeyJoint = PropertiesManager.getRequestHeaderJoint();
        String headerKeySuffix = keyFunction.apply(PropertiesManager.getRequestHeaderProperties());
        if (StrUtil.isBlank(headerKeyPrefix)) {
            return getRequest().getHeader(headerKeySuffix);
        }
        return getRequest().getHeader(String.join(headerKeyJoint, headerKeyPrefix, headerKeySuffix));
    }

    /**
     * 请求属性（获取）：加密后的参数文本
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    public static String getRequestAttributeEncryptedParameterContent() {
        Object content = getRequest().getAttribute(ENCRYPTED_PARAMETER_CONTENT_ATTRIBUTE_NAME);
        return content instanceof String str ? str : StrUtil.EMPTY;
    }

    /**
     * 请求属性（设置）：加密后的参数文本
     *
     * @param content 加密后的参数文本
     * @author JiaoJinxin
     */
    public static void setRequestAttributeEncryptedParameterContent(String content) {
        if (StringUtils.hasText(content)) {
            getRequest().setAttribute(ENCRYPTED_PARAMETER_CONTENT_ATTRIBUTE_NAME, content);
        }
    }

    /**
     * 获取{@link HttpServletResponse}
     *
     * @return jakarta.servlet.http.HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        return getServletRequestAttributes().getResponse();
    }
}
