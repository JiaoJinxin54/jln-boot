package top.jiaojinxin.system.log.logback.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;
import top.jiaojinxin.common.util.PropertiesManager;
import top.jiaojinxin.system.util.LogUtil;

import java.io.IOException;

/**
 * logback过滤器
 *
 * @author JiaoJinxin
 */
@RequiredArgsConstructor
public class LogbackFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        LogUtil.setTraceId(PropertiesManager.getHeaderTrace(request));
        filterChain.doFilter(request, response);
        LogUtil.clearTraceId();
    }
}
