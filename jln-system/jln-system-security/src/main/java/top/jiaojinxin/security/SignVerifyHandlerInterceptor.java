package top.jiaojinxin.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.jiaojinxin.common.exception.BizException;
import top.jiaojinxin.common.properties.SignProperties;
import top.jiaojinxin.common.util.PropertiesManager;
import top.jiaojinxin.sign.SignDTO;
import top.jiaojinxin.util.SignUtil;

/**
 * 签名验证拦截器
 *
 * @author JiaoJinxin
 */
@RequiredArgsConstructor
public class SignVerifyHandlerInterceptor implements HandlerInterceptor {

    private final SignProperties signProperties;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        if (handler instanceof HandlerMethod) {
            return doSignVerify(request);
        }
        return true;
    }

    /**
     * 执行验签
     *
     * @param request {@link HttpServletRequest}
     */
    private boolean doSignVerify(HttpServletRequest request) {
        // 构建签名参数
        SignDTO signDTO = generateSignDTO(request);
        // 获取签名值
        String signValue = PropertiesManager.getHeaderSign(request);
        // 验证签名
        if (SignUtil.verify(signDTO, signValue)) {
            return true;
        }
        throw new BizException(signProperties.getExceptionCode().getIllegalRequest());
    }

    /**
     * 构建签名参数
     *
     * @param request {@link HttpServletRequest}
     * @return top.jiaojinxin.jln.sign.model.SignDTO
     */
    private SignDTO generateSignDTO(HttpServletRequest request) {
        SignDTO signDTO = new SignDTO();
        signDTO.setClientCode(PropertiesManager.getHeaderClientCode(request));
        signDTO.setTimestamp(PropertiesManager.getHeaderTimestamp(request));
        signDTO.setUid(PropertiesManager.getHeaderUid(request));
        signDTO.setContent(PropertiesManager.getAttributeEncryptedParameterContent(request));
        signDTO.setAlgorithm(PropertiesManager.getHeaderAlgorithm(request));
        signDTO.setSalt(PropertiesManager.getHeaderSalt(request));
        return signDTO;
    }
}
