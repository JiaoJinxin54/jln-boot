package top.jiaojinxin.security;

import cn.hutool.crypto.asymmetric.KeyType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.jiaojinxin.common.exception.BizException;
import top.jiaojinxin.sign.SignDTO;
import top.jiaojinxin.util.HttpServletUtil;
import top.jiaojinxin.util.PropertiesManager;
import top.jiaojinxin.util.SignUtil;

/**
 * 签名验证拦截器
 *
 * @author JiaoJinxin
 */
@RequiredArgsConstructor
public class SignVerifyHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) {
        if (handler instanceof HandlerMethod) {
            return doSignVerify();
        }
        return true;
    }

    /**
     * 执行验签
     */
    private boolean doSignVerify() {
        // 构建签名参数
        SignDTO signDTO = generateSignDTO();
        // 获取签名值
        String signValue = HttpServletUtil.getRequestHeaderSign();
        // 验证签名
        if (SignUtil.verify(signDTO, signValue)) {
            return true;
        }
        throw new BizException(PropertiesManager.getIllegalRequest());
    }

    /**
     * 构建签名参数
     *
     * @return top.jiaojinxin.jln.sign.model.SignDTO
     */
    private SignDTO generateSignDTO() {
        SignDTO signDTO = new SignDTO();
        signDTO.setClientCode(HttpServletUtil.getRequestHeaderClientCode());
        signDTO.setTimestamp(HttpServletUtil.getRequestHeaderTimestamp());
        signDTO.setUid(HttpServletUtil.getRequestHeaderUid());
        signDTO.setContent(HttpServletUtil.getRequestAttributeEncryptedParameterContent());
        signDTO.setSalt(SignUtil.decrypt(signDTO.getClientCode(), HttpServletUtil.getRequestHeaderSalt(), KeyType.PublicKey));
        signDTO.setAlgorithm(HttpServletUtil.getRequestHeaderAlgorithm());
        return signDTO;
    }
}
