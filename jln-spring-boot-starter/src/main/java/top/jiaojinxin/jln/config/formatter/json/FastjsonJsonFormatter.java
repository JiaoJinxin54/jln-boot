package top.jiaojinxin.jln.config.formatter.json;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import top.jiaojinxin.jln.log.JsonFormatter;

/**
 * 基于fastjson的JSON序列化
 *
 * @author JiaoJinxin
 */
@ConditionalOnClass(JSONObject.class)
@ConditionalOnMissingBean(JsonFormatter.class)
@AutoConfigureBefore(GsonJsonFormatter.class)
public class FastjsonJsonFormatter implements JsonFormatter {

    @Override
    public String toStr(Object arg) {
        return JSONObject.toJSONString(arg);
    }
}
