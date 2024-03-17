package top.jiaojinxin.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import top.jiaojinxin.properties.logback.LogbackProperties;

import java.io.Serial;
import java.io.Serializable;

/**
 * 日志配置
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
@ConfigurationProperties(prefix = LogProperties.PREFIX)
public class LogProperties implements Serializable {
    @Serial
    private static final long serialVersionUID = -4505825791042182583L;

    /**
     * 日志配置
     */
    public LogProperties() {
    }

    /**
     * 前缀
     */
    public static final String PREFIX = "jln.log";

    /**
     * 项目名称，默认：spring.application.name
     */
    private String project = "${spring.application.name}";

    /**
     * 模块名称，默认：spring.application.name
     */
    private String model = "${spring.application.name}";

    /**
     * logback相关配置
     */
    @NestedConfigurationProperty
    private LogbackProperties logback = new LogbackProperties();
}
