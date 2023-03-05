package top.jiaojinxin.jln.config.formatter.json;

import cn.hutool.json.JSONUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import top.jiaojinxin.jln.log.JsonFormatter;

/**
 * 基于hutool的JSON序列化
 *
 * @author JiaoJinxin
 */
@ConditionalOnClass(JSONUtil.class)
@ConditionalOnMissingBean(JsonFormatter.class)
public class HutoolJsonFormatter implements JsonFormatter {

    @Override
    public String toStr(Object arg) {
        return JSONUtil.toJsonStr(arg);
    }
}
