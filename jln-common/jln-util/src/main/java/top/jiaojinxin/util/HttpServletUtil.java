package top.jiaojinxin.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.jiaojinxin.common.exception.BizException;
import top.jiaojinxin.common.util.PropertiesManager;

/**
 * Servlet工具类
 *
 * @author JiaoJinxin
 */
public class HttpServletUtil {
    private HttpServletUtil() {
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
     * 获取{@link HttpServletResponse}
     *
     * @return jakarta.servlet.http.HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        return getServletRequestAttributes().getResponse();
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
}
