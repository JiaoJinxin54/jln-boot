package top.jiaojinxin.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.jiaojinxin.common.exception.BizException;
import top.jiaojinxin.common.properties.SecurityProperties;
import top.jiaojinxin.common.properties.SignProperties;
import top.jiaojinxin.common.util.PropertiesManager;

import java.time.Duration;
import java.util.regex.Pattern;

/**
 * 接口幂等拦截器
 *
 * @author JiaoJinxin
 */
@RequiredArgsConstructor
public class IdempotentHandlerInterceptor implements HandlerInterceptor {

    private static final Pattern TIMESTAMP_PATTERN = Pattern.compile("^\\d+$");

    private final SecurityIdempotentCache securityIdempotentCache;

    private final SignProperties signProperties;

    private final SecurityProperties securityProperties;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        if (handler instanceof HandlerMethod) {
            return doIdempotentVerification(request);
        }
        return true;
    }

    /**
     * 执行幂等验证
     *
     * @param request {@link HttpServletRequest}
     * @return boolean
     */
    private boolean doIdempotentVerification(HttpServletRequest request) {
        // 接口防刷：时间戳验证
        timestampValidated(request);
        // 接口防刷：唯一键验证
        uidValidated(request);
        return true;
    }

    /**
     * 接口防刷之时间戳验证
     *
     * @param request {@link HttpServletRequest}
     */
    private void timestampValidated(HttpServletRequest request) {
        String idempotentTimestamp = PropertiesManager.getHeaderTimestamp(request);
        if (!StringUtils.hasText(idempotentTimestamp) || !TIMESTAMP_PATTERN.matcher(idempotentTimestamp).find()) {
            throwBizException();
        }
        // 当前时间戳 - 请求头中的时间戳
        long timestampDiff = System.currentTimeMillis() - Long.parseLong(idempotentTimestamp);
        /*
          以下两种情况不允许请求通过：
          1. 请求时发送的时间戳在当前时间之前，且已超过指定的有效时差（即：idempotentProperties.getUidExpire）时，不允许请求通过；
          2. 不同服务器的时间可能存在差异，可能发送请求的服务器时间早于接收请求的服务器，就会出现请求时获取到的时间戳在到达接收请求
             的服务器时仍比接收请求的服务器的当前时间还要晚（即大于当前时间），这里允许两个服务器之前的时间存在最大不超过指定有效时
             差（即：idempotentProperties.getTimeDiff）;
         */
        if (timestampDiff > securityProperties.getUidExpire() || -timestampDiff > securityProperties.getTimeDiff()) {
            throwBizException();
        }
    }

    /**
     * 接口防刷之唯一键验证
     *
     * @param request {@link HttpServletRequest}
     */
    private void uidValidated(HttpServletRequest request) {
        String uid = PropertiesManager.getHeaderUid(request);
        if (!StringUtils.hasText(uid)) {
            throwBizException();
        }
        Object value = Thread.currentThread().threadId();
        Duration duration = Duration.ofMillis(securityProperties.getUidExpire());
        if (!securityIdempotentCache.setIfAbsent(uid, value, duration)) {
            throwBizException();
        }
    }

    /**
     * 抛出非法请求异常
     */
    private void throwBizException() {
        throw new BizException(this.signProperties.getExceptionCode().getIllegalRequest());
    }
}
