package top.jiaojinxin.properties.logback;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.unit.DataSize;

import java.io.Serial;
import java.io.Serializable;

/**
 * 循环滚动配置
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
public class LogbackRollingPolicy implements Serializable {
    @Serial
    private static final long serialVersionUID = -4004722088075289614L;

    /**
     * 循环滚动配置
     */
    public LogbackRollingPolicy() {
    }

    /**
     * 日志循环滚动单个文件最大容量，默认：500MB
     */
    private DataSize maxFileSize = DataSize.ofMegabytes(500);

    /**
     * 日志循环滚动文件总容量，默认：20GB
     */
    private DataSize totalSizeCap = DataSize.ofGigabytes(20);

    /**
     * 日志循环滚动文件保留天数，默认：15
     */
    private Integer maxHistory = 15;
}