package top.jiaojinxin.system.properties.logback;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serial;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * logback日志打印到文件的相关配置
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
public class LogbackFile implements Serializable {
    @Serial
    private static final long serialVersionUID = -8571008061785123326L;

    /**
     * 文件日志打印模式
     */
    private String pattern = "%d{yyyy-MM-dd HH:mm:ss.SSS}|%p|%traceId|%t|%c[%L] : %m%n";

    /**
     * 编码格式
     */
    private Charset charset = StandardCharsets.UTF_8;

    /**
     * 日志文件基础路径，默认：/opt/logs
     */
    private String basePath = "/opt/logs";

    /**
     * 循环滚动配置
     */
    @NestedConfigurationProperty
    private LogbackRollingPolicy rollingPolicy = new LogbackRollingPolicy();

    /**
     * 异步打印日志配置
     */
    @NestedConfigurationProperty
    private LogbackAsync async = new LogbackAsync();
}
