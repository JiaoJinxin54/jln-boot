package top.jiaojinxin.system.properties.logback;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serial;
import java.io.Serializable;

/**
 * logback日志相关配置
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
public class LogbackProperties implements Serializable {
    @Serial
    private static final long serialVersionUID = 9094603976244726569L;

    /**
     * logback日志打印到控制台配置
     */
    @NestedConfigurationProperty
    private LogbackConsole console = new LogbackConsole();

    /**
     * logback日志打印到文件配置
     */
    @NestedConfigurationProperty
    private LogbackFile file = new LogbackFile();
}
