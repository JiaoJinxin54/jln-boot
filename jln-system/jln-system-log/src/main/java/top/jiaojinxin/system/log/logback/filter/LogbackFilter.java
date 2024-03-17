package top.jiaojinxin.system.log.logback.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;
import top.jiaojinxin.util.HttpServletUtil;
import top.jiaojinxin.util.LogUtil;

import java.io.IOException;

/**
 * logback过滤器
 *
 * @author JiaoJinxin
 */
@RequiredArgsConstructor
public class LogbackFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        LogUtil.setTraceId(HttpServletUtil.getRequestHeaderTrace());
        filterChain.doFilter(request, response);
        LogUtil.clearTraceId();
    }
}
