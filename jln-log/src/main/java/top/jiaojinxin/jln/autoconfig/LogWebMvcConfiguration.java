package top.jiaojinxin.jln.autoconfig;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.jiaojinxin.jln.log.AuditLogHandler;
import top.jiaojinxin.jln.log.AuditLogHandlerInterceptor;
import top.jiaojinxin.jln.log.event.AuditLogEvent;

/**
 * {@link WebMvcConfigurer}实现，用于注入拦截器
 *
 * @author JiaoJinxin
 */
public class LogWebMvcConfiguration implements WebMvcConfigurer {

    private final AuditLogHandler<? extends AuditLogEvent> auditLogHandler;

    /**
     * 构造方法
     *
     * @param auditLogHandler 审计日志处理器
     * @author JiaoJinxin
     */
    public LogWebMvcConfiguration(AuditLogHandler<? extends AuditLogEvent> auditLogHandler) {
        this.auditLogHandler = auditLogHandler;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuditLogHandlerInterceptor<>(auditLogHandler)).addPathPatterns("/**");
    }
}
