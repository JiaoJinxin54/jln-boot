package top.jiaojinxin.properties.logback;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * logback日志打印到控制台的相关配置
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
public class LogbackConsole implements Serializable {
    @Serial
    private static final long serialVersionUID = 1077690438833945120L;

    /**
     * 控制台日志打印模式
     */
    private String pattern = "%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(%5p) %clr(${PID:- }){magenta} %clr(---){faint} %clr(%traceId){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}[%L]){cyan} %clr(:){faint} %m%n";

    /**
     * 编码格式
     */
    private Charset charset = StandardCharsets.UTF_8;
}
