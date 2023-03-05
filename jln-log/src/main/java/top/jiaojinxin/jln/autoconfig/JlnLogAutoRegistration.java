package top.jiaojinxin.jln.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import top.jiaojinxin.jln.log.JsonFormatter;
import top.jiaojinxin.jln.log.StringFormatter;
import top.jiaojinxin.jln.util.LogManager;

/**
 * 日志相关自动注册
 *
 * @author JiaoJinxin
 */
public class JlnLogAutoRegistration {

    /**
     * 将JSON序列化程序注册到静态工具类中，以便在非Spring Bean场景中静态调用
     *
     * @param jsonFormatter JSON序列化程序
     * @author JiaoJinxin
     */
    @Autowired(required = false)
    public void setJsonFormatter(JsonFormatter jsonFormatter) {
        LogManager.setJsonFormatter(jsonFormatter);
    }

    /**
     * 将文本格式化处理程序注册到静态工具类中，以便在非Spring Bean场景中静态调用
     *
     * @param stringFormatter 文本格式化处理程序
     * @author JiaoJinxin
     */
    @Autowired(required = false)
    public void setStringFormatter(StringFormatter stringFormatter) {
        LogManager.setStringFormatter(stringFormatter);
    }
}
