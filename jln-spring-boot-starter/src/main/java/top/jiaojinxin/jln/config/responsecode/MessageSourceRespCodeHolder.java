package top.jiaojinxin.jln.config.responsecode;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import top.jiaojinxin.jln.respcode.RespCodeHolder;

import java.util.Locale;

/**
 * 基于{@link MessageSource}的{@link RespCodeHolder}实现
 *
 * @author JiaoJinxin
 */
@ConditionalOnClass({MessageSource.class, RespCodeHolder.class})
@ConditionalOnBean(MessageSource.class)
@ConditionalOnMissingBean(RespCodeHolder.class)
public class MessageSourceRespCodeHolder implements RespCodeHolder {

    private final MessageSource messageSource;

    public MessageSourceRespCodeHolder(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMsg(String code, String[] args, String defaultValue, Locale locale) {
        return this.messageSource.getMessage(code, args, defaultValue, locale);
    }
}
