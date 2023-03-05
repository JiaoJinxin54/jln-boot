package top.jiaojinxin.jln.config.formatter.string;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import top.jiaojinxin.jln.log.StringFormatter;

/**
 * 基于{@link String#format}的{@link StringFormatter}实现
 *
 * @author JiaoJinxin
 */
@ConditionalOnMissingBean(StringFormatter.class)
public class DefaultStringFormatter implements StringFormatter {

    private final static String EMPTY_STRING = "";

    private final static String DEFAULT_PLACE_HOLDER = "%s";

    @Override
    public String format(String strPattern, String placeHolder, Object... argArray) {
        if (strPattern == null) {
            return EMPTY_STRING;
        }
        return String.format(strPattern, argArray);
    }

    @Override
    public String defaultPlaceHolder() {
        return DEFAULT_PLACE_HOLDER;
    }
}
