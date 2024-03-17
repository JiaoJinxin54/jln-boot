package top.jiaojinxin.util;

import lombok.Setter;
import top.jiaojinxin.properties.RequestHeaderProperties;
import top.jiaojinxin.properties.ResponseI18nCodeProperties;

/**
 * 核心管理类
 *
 * @author JiaoJinxin
 */
public class PropertiesManager {

    private PropertiesManager() {
    }

    @Setter
    private static ResponseI18nCodeProperties responseI18nCodeProperties;

    @Setter
    private static RequestHeaderProperties requestHeaderProperties;

    /**
     * 响应码：成功
     *
     * @return java.lang.String
     */
    public static String getSuccess() {
        return PropertiesManager.responseI18nCodeProperties.getSuccess();
    }

    /**
     * 响应码：失败
     *
     * @return java.lang.String
     */
    public static String getFail() {
        return PropertiesManager.responseI18nCodeProperties.getFail();
    }

    /**
     * 响应码：系统异常
     *
     * @return java.lang.String
     */
    public static String getFailSys() {
        return PropertiesManager.responseI18nCodeProperties.getFailSys();
    }

    /**
     * 响应码：业务异常
     *
     * @return java.lang.String
     */
    public static String getFailBiz() {
        return PropertiesManager.responseI18nCodeProperties.getFailBiz();
    }

    /**
     * 响应码：无法获取ServletRequestAttributes
     *
     * @return java.lang.String
     */
    public static String getServletRequestAttributesNotFound() {
        return PropertiesManager.responseI18nCodeProperties.getServletRequestAttributesNotFound();
    }

    /**
     * 响应码：参数异常
     *
     * @return java.lang.String
     */
    public static String getParamError() {
        return PropertiesManager.responseI18nCodeProperties.getParamError();
    }

    /**
     * 响应码：接口地址无效
     *
     * @return java.lang.String
     */
    public static String getUrlInvalid() {
        return PropertiesManager.responseI18nCodeProperties.getUrlInvalid();
    }

    /**
     * 响应码：非法请求
     *
     * @return java.lang.String
     */
    public static String getIllegalRequest() {
        return PropertiesManager.responseI18nCodeProperties.getIllegalRequest();
    }

    /**
     * 请求头配置：完整配置
     *
     * @return top.jiaojinxin.properties.RequestHeaderProperties
     */
    public static RequestHeaderProperties getRequestHeaderProperties() {
        return PropertiesManager.requestHeaderProperties;
    }

    /**
     * 请求头配置：前缀
     *
     * @return java.lang.String
     */
    public static String getRequestHeaderPrefix() {
        return PropertiesManager.requestHeaderProperties.getPrefix();
    }

    /**
     * 请求头配置：连接符
     *
     * @return java.lang.String
     */
    public static String getRequestHeaderJoint() {
        return PropertiesManager.requestHeaderProperties.getJoint();
    }
}