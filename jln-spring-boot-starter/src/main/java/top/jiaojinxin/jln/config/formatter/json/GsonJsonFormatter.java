package top.jiaojinxin.jln.config.formatter.json;

import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import top.jiaojinxin.jln.log.JsonFormatter;

/**
 * 基于gson的JSON序列化
 *
 * @author JiaoJinxin
 */
@ConditionalOnClass(Gson.class)
@ConditionalOnMissingBean(JsonFormatter.class)
@AutoConfigureBefore(HutoolJsonFormatter.class)
public class GsonJsonFormatter implements JsonFormatter {

    private final Gson gson = new Gson();

    @Override
    public String toStr(Object arg) {
        return this.gson.toJson(arg);
    }
}
