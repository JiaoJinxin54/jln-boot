package top.jiaojinxin.jln.config.formatter.string;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.StrUtil;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import top.jiaojinxin.jln.log.StringFormatter;

import static cn.hutool.core.text.StrPool.EMPTY_JSON;

/**
 * 基于hutool的{@link StringFormatter}实现
 *
 * @author JiaoJinxin
 */
@ConditionalOnClass(StrFormatter.class)
@ConditionalOnMissingBean(StringFormatter.class)
@AutoConfigureBefore(DefaultStringFormatter.class)
public class HutoolStringFormatter implements StringFormatter {
    @Override
    public String format(String strPattern, String placeHolder, Object... argArray) {
        if (strPattern == null) {
            return StrUtil.EMPTY;
        }
        return StrFormatter.formatWith(strPattern, placeHolder, argArray);
    }

    @Override
    public String defaultPlaceHolder() {
        return EMPTY_JSON;
    }
}
