package top.jiaojinxin.common.util;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;
import org.springframework.util.StringUtils;
import top.jiaojinxin.common.properties.RequestHeaderProperties;
import top.jiaojinxin.common.properties.ResponseI18nCodeProperties;

import java.util.function.Function;

/**
 * 核心管理类
 *
 * @author JiaoJinxin
 */
public class PropertiesManager {

    /**
     * 请求属性名称（Request Attribute Name）：加密参数内容
     */
    private static final String ENCRYPTED_PARAMETER_CONTENT_ATTRIBUTE_NAME = "ATTRIBUTE_NAME_ENCRYPTED_PARAMETER_CONTENT";

    private PropertiesManager() {
    }

    @Setter
    private static ResponseI18nCodeProperties responseI18nCodeProperties;

    @Setter
    private static RequestHeaderProperties requestHeaderProperties;

    /**
     * 响应码（获取）：成功
     *
     * @return java.lang.String
     */
    public static String getSuccess() {
        return PropertiesManager.responseI18nCodeProperties.getSuccess();
    }

    /**
     * 响应码（获取）：失败
     *
     * @return java.lang.String
     */
    public static String getFail() {
        return PropertiesManager.responseI18nCodeProperties.getFail();
    }

    /**
     * 响应码（获取）：系统异常
     *
     * @return java.lang.String
     */
    public static String getFailSys() {
        return PropertiesManager.responseI18nCodeProperties.getFailSys();
    }

    /**
     * 响应码（获取）：业务异常
     *
     * @return java.lang.String
     */
    public static String getFailBiz() {
        return PropertiesManager.responseI18nCodeProperties.getFailBiz();
    }

    /**
     * 响应码（获取）：无法获取ServletRequestAttributes
     *
     * @return java.lang.String
     */
    public static String getServletRequestAttributesNotFound() {
        return PropertiesManager.responseI18nCodeProperties.getServletRequestAttributesNotFound();
    }

    /**
     * 响应码（获取）：参数异常
     *
     * @return java.lang.String
     */
    public static String getParamError() {
        return PropertiesManager.responseI18nCodeProperties.getParamError();
    }

    /**
     * 响应码（获取）：接口地址无效
     *
     * @return java.lang.String
     */
    public static String getUrlInvalid() {
        return PropertiesManager.responseI18nCodeProperties.getUrlInvalid();
    }

    /**
     * 请求头（获取）：签名值
     *
     * @param request {@link HttpServletRequest}
     * @return java.lang.String
     */
    public static String getHeaderSign(HttpServletRequest request) {
        return getHeader(request, RequestHeaderProperties::getSign);
    }

    /**
     * 请求头（获取）：客户端标识
     *
     * @param request {@link HttpServletRequest}
     * @return java.lang.String
     */
    public static String getHeaderClientCode(HttpServletRequest request) {
        return getHeader(request, RequestHeaderProperties::getClient);
    }

    /**
     * 请求头（获取）：时间戳
     *
     * @param request {@link HttpServletRequest}
     * @return java.lang.String
     */
    public static String getHeaderTimestamp(HttpServletRequest request) {
        return getHeader(request, RequestHeaderProperties::getTimestamp);
    }

    /**
     * 请求头（获取）：唯一键
     *
     * @param request {@link HttpServletRequest}
     * @return java.lang.String
     */
    public static String getHeaderUid(HttpServletRequest request) {
        return getHeader(request, RequestHeaderProperties::getUid);
    }

    /**
     * 请求头（获取）：加密算法
     *
     * @param request {@link HttpServletRequest}
     * @return java.lang.String
     */
    public static String getHeaderAlgorithm(HttpServletRequest request) {
        return getHeader(request, RequestHeaderProperties::getAlgorithm);
    }

    /**
     * 请求头（获取）：加密算法盐值
     *
     * @param request {@link HttpServletRequest}
     * @return java.lang.String
     * @author JiaoJinxin
     */
    public static String getHeaderSalt(HttpServletRequest request) {
        return getHeader(request, RequestHeaderProperties::getSalt);
    }

    /**
     * 请求头（获取）：链路追踪标识
     *
     * @param request {@link HttpServletRequest}
     * @return java.lang.String
     * @author JiaoJinxin
     */
    public static String getHeaderTrace(HttpServletRequest request) {
        return getHeader(request, RequestHeaderProperties::getTrace);
    }

    /**
     * 请求属性（获取）：加密后的参数文本
     *
     * @param request {@link HttpServletRequest}
     * @return java.lang.String
     * @author JiaoJinxin
     */
    public static String getAttributeEncryptedParameterContent(HttpServletRequest request) {
        Object content = request.getAttribute(ENCRYPTED_PARAMETER_CONTENT_ATTRIBUTE_NAME);
        if (content instanceof String str) {
            return str;
        }
        return StrUtil.EMPTY;
    }

    /**
     * 请求属性（设置）：加密后的参数文本
     *
     * @param request {@link HttpServletRequest}
     * @param content 加密后的参数文本
     * @author JiaoJinxin
     */
    public static void setAttributeEncryptedParameterContent(HttpServletRequest request, String content) {
        if (StringUtils.hasText(content)) {
            request.setAttribute(ENCRYPTED_PARAMETER_CONTENT_ATTRIBUTE_NAME, content);
        }
    }

    /**
     * 获取header内容
     *
     * @param request     {@link HttpServletRequest}
     * @param keyFunction header名称获取逻辑
     * @return java.lang.String
     * @author JiaoJinxin
     */
    private static String getHeader(HttpServletRequest request, Function<RequestHeaderProperties, String> keyFunction) {
        String headerKeyPrefix = PropertiesManager.requestHeaderProperties.getPrefix();
        String headerKeyJoint = PropertiesManager.requestHeaderProperties.getJoint();
        String headerKeySuffix = keyFunction.apply(PropertiesManager.requestHeaderProperties);
        if (StrUtil.isBlank(headerKeyPrefix)) {
            return request.getHeader(headerKeySuffix);
        }
        return request.getHeader(String.join(headerKeyJoint, headerKeyPrefix, headerKeySuffix));
    }
}