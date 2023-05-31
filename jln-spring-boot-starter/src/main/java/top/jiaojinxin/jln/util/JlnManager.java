package top.jiaojinxin.jln.util;

import jakarta.validation.Validator;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.transaction.support.TransactionTemplate;
import top.jiaojinxin.jln.event.Event;
import top.jiaojinxin.jln.event.EventHandler;
import top.jiaojinxin.jln.event.EventHandlerRepository;
import top.jiaojinxin.jln.event.EventPublisher;
import top.jiaojinxin.jln.log.JsonFormatter;
import top.jiaojinxin.jln.log.StringFormatter;
import top.jiaojinxin.jln.mp.MpCurrUserHolder;
import top.jiaojinxin.jln.mp.model.MpCurrUser;
import top.jiaojinxin.jln.oss.OssTemplate;
import top.jiaojinxin.jln.properties.JlnEventProperties;
import top.jiaojinxin.jln.properties.JlnMpProperties;
import top.jiaojinxin.jln.properties.JlnRedisProperties;
import top.jiaojinxin.jln.properties.JlnRespCodeProperties;
import top.jiaojinxin.jln.respcode.LocaleHolder;
import top.jiaojinxin.jln.respcode.RespCodeHolder;

import java.util.List;

/**
 * 静态资源管理类
 *
 * @author JiaoJinxin
 */
public class JlnManager {
    private JlnManager() {
    }

    // region RespCode模块

    /**
     * 获取{@link JlnRespCodeProperties}
     *
     * @return top.jiaojinxin.jln.properties.JlnRespCodeProperties
     * @author JiaoJinxin
     */
    public static JlnRespCodeProperties getRespCodeProperties() {
        return RespCodeManager.getRespCodeProperties();
    }

    /**
     * 设置{@link JlnRespCodeProperties}
     *
     * @param respCodeProperties {@link JlnRespCodeProperties}
     * @author JiaoJinxin
     */
    public static void setRespCodeProperties(JlnRespCodeProperties respCodeProperties) {
        RespCodeManager.setRespCodeProperties(respCodeProperties);
    }

    /**
     * 获取{@link LocaleHolder}
     *
     * @return top.jiaojinxin.jln.respcode.LocaleHolder
     * @author JiaoJinxin
     */
    public static LocaleHolder getLocaleHolder() {
        return RespCodeManager.getLocaleHolder();
    }

    /**
     * 设置{@link LocaleHolder}
     *
     * @param localeHolder {@link LocaleHolder}
     * @author JiaoJinxin
     */
    public static void setLocaleHolder(LocaleHolder localeHolder) {
        RespCodeManager.setLocaleHolder(localeHolder);
    }

    /**
     * 获取{@link RespCodeHolder}
     *
     * @return top.jiaojinxin.jln.respcode.RespCodeHolder
     * @author JiaoJinxin
     */
    public static RespCodeHolder getRespCodeHolder() {
        return RespCodeManager.getRespCodeHolder();
    }

    /**
     * 设置{@link RespCodeHolder}
     *
     * @param respCodeHolder {@link RespCodeHolder}
     * @author JiaoJinxin
     */
    public static void setRespCodeHolder(RespCodeHolder respCodeHolder) {
        RespCodeManager.setRespCodeHolder(respCodeHolder);
    }

    // endregion

    // region Exception模块

    /**
     * 获取{@link Validator}
     *
     * @return jakarta.validation.Validator
     * @author JiaoJinxin
     */
    public static Validator getValidator() {
        return ExceptionManager.getValidator();
    }

    /**
     * 设置{@link Validator}
     *
     * @param validator {@link Validator}
     * @author JiaoJinxin
     */
    public static void setValidator(Validator validator) {
        ExceptionManager.setValidator(validator);
    }

    // endregion

    // region Event模块

    /**
     * 获取{@link JlnEventProperties}
     *
     * @return top.jiaojinxin.jln.properties.JlnEventProperties
     * @author JiaoJinxin
     */
    public static JlnEventProperties getEventProperties() {
        return EventManager.getEventProperties();
    }

    /**
     * 设置{@link JlnEventProperties}
     *
     * @param eventProperties {@link JlnEventProperties}
     * @author JiaoJinxin
     */
    public static void setEventProperties(JlnEventProperties eventProperties) {
        EventManager.setEventProperties(eventProperties);
    }

    /**
     * 获取{@link EventHandler}集合
     *
     * @return java.util.List
     * @author JiaoJinxin
     */
    public static List<EventHandler<? extends Event>> getEventHandlers() {
        return EventManager.getEventHandlers();
    }

    /**
     * 设置{@link EventHandler}集合
     *
     * @param eventHandlers {@link EventHandler}集合
     * @author JiaoJinxin
     */
    public static void setEventHandlers(List<EventHandler<? extends Event>> eventHandlers) {
        EventManager.setEventHandlers(eventHandlers);
    }

    /**
     * 获取{@link EventHandlerRepository}
     *
     * @return top.jiaojinxin.jln.event.EventHandlerRepository
     * @author JiaoJinxin
     */
    public static EventHandlerRepository getEventHandlerRepository() {
        return EventManager.getEventHandlerRepository();
    }

    /**
     * 设置{@link EventHandlerRepository}
     *
     * @param eventHandlerRepository {@link EventHandlerRepository}
     * @author JiaoJinxin
     */
    public static void setEventHandlerRepository(EventHandlerRepository eventHandlerRepository) {
        EventManager.setEventHandlerRepository(eventHandlerRepository);
    }

    /**
     * 获取{@link EventPublisher}
     *
     * @return top.jiaojinxin.jln.event.EventPublisher
     * @author JiaoJinxin
     */
    public static EventPublisher getEventPublisher() {
        return EventManager.getEventPublisher();
    }

    /**
     * 设置{@link EventPublisher}
     *
     * @param eventPublisher {@link EventPublisher}
     * @author JiaoJinxin
     */
    public static void setEventPublisher(EventPublisher eventPublisher) {
        EventManager.setEventPublisher(eventPublisher);
    }

    // endregion

    // region Log模块

    /**
     * 获取{@link JsonFormatter}
     *
     * @return top.jiaojinxin.jln.log.JsonFormatter
     * @author JiaoJinxin
     */
    public static JsonFormatter getJsonFormatter() {
        return LogManager.getJsonFormatter();
    }

    /**
     * 设置{@link JsonFormatter}
     *
     * @param jsonFormatter {@link JsonFormatter}
     * @author JiaoJinxin
     */
    public static void setJsonFormatter(JsonFormatter jsonFormatter) {
        LogManager.setJsonFormatter(jsonFormatter);
    }

    /**
     * 获取{@link StringFormatter}
     *
     * @return top.jiaojinxin.jln.log.StringFormatter
     * @author JiaoJinxin
     */
    public static StringFormatter getStringFormatter() {
        return LogManager.getStringFormatter();
    }

    /**
     * 设置{@link StringFormatter}
     *
     * @param stringFormatter {@link StringFormatter}
     * @author JiaoJinxin
     */
    public static void setStringFormatter(StringFormatter stringFormatter) {
        LogManager.setStringFormatter(stringFormatter);
    }

    // endregion

    // region Redis模块

    /**
     * 获取{@link JlnRedisProperties}
     *
     * @return top.jiaojinxin.jln.properties.JlnRedisProperties
     * @author JiaoJinxin
     */
    public static JlnRedisProperties getJlnRedisProperties() {
        return RedisManager.getJlnRedisProperties();
    }

    /**
     * 设置{@link JlnRedisProperties}
     *
     * @param jlnRedisProperties {@link JlnRedisProperties}
     * @author JiaoJinxin
     */
    public static void setJlnRedisProperties(JlnRedisProperties jlnRedisProperties) {
        RedisManager.setJlnRedisProperties(jlnRedisProperties);
    }

    /**
     * 获取{@link RedisTemplate}
     *
     * @return org.springframework.data.redis.core.RedisTemplate
     * @author JiaoJinxin
     */
    public static RedisTemplate<String, String> getRedisTemplate() {
        return RedisManager.getRedisTemplate();
    }

    /**
     * 设置{@link RedisTemplate}
     *
     * @param redisTemplate {@link RedisTemplate}
     * @author JiaoJinxin
     */
    public static void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        RedisManager.setRedisTemplate(redisTemplate);
    }

    /**
     * 获取{@link ValueOperations}
     *
     * @return org.springframework.data.redis.core.ValueOperations
     * @author JiaoJinxin
     */
    public static ValueOperations<String, String> value() {
        return RedisManager.value();
    }

    /**
     * 获取{@link HashOperations}
     *
     * @return org.springframework.data.redis.core.HashOperations
     * @author JiaoJinxin
     */
    public static HashOperations<String, String, String> hash() {
        return RedisManager.hash();
    }

    /**
     * 获取{@link ListOperations}
     *
     * @return org.springframework.data.redis.core.ListOperations
     * @author JiaoJinxin
     */
    public static ListOperations<String, String> list() {
        return RedisManager.list();
    }

    /**
     * 获取{@link SetOperations}
     *
     * @return org.springframework.data.redis.core.SetOperations
     * @author JiaoJinxin
     */
    public static SetOperations<String, String> set() {
        return RedisManager.set();
    }

    /**
     * 获取{@link ZSetOperations}
     *
     * @return org.springframework.data.redis.core.ZSetOperations
     * @author JiaoJinxin
     */
    public static ZSetOperations<String, String> zSet() {
        return RedisManager.zSet();
    }

    /**
     * 获取{@link GeoOperations}
     *
     * @return org.springframework.data.redis.core.GeoOperations
     * @author JiaoJinxin
     */
    public static GeoOperations<String, String> geo() {
        return RedisManager.geo();
    }

    // endregion

    // region MybatisPlus模块

    /**
     * 获取{@link JlnMpProperties}
     *
     * @return top.jiaojinxin.jln.properties.JlnMpProperties
     * @author JiaoJinxin
     */
    public static JlnMpProperties getMpProperties() {
        return MpManager.getMpProperties();
    }

    /**
     * 设置{@link JlnMpProperties}
     *
     * @param mpProperties {@link JlnMpProperties}
     * @author JiaoJinxin
     */
    public static void setMpProperties(JlnMpProperties mpProperties) {
        MpManager.setMpProperties(mpProperties);
    }

    /**
     * 获取{@link MpCurrUserHolder}
     *
     * @return top.jiaojinxin.jln.mp.MpCurrUserSupplier
     * @author JiaoJinxin
     */
    public static MpCurrUserHolder<? extends MpCurrUser> getCurrUserHolder() {
        return MpManager.getCurrUserHolder();
    }

    /**
     * 设置{@link MpCurrUserHolder}
     *
     * @param currUserSupplier {@link MpCurrUserHolder}
     * @author JiaoJinxin
     */
    public static void setCurrUserHolder(MpCurrUserHolder<? extends MpCurrUser> currUserSupplier) {
        MpManager.setCurrUserHolder(currUserSupplier);
    }

    /**
     * 获取{@link TransactionTemplate}
     *
     * @return org.springframework.transaction.support.TransactionTemplate
     * @author JiaoJinxin
     */
    public static TransactionTemplate getTransactionTemplate() {
        return MpManager.getTransactionTemplate();
    }

    /**
     * 设置{@link TransactionTemplate}
     *
     * @param transactionTemplate {@link TransactionTemplate}
     * @author JiaoJinxin
     */
    public static void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        MpManager.setTransactionTemplate(transactionTemplate);
    }

    // endregion

    // region oss模块

    /**
     * 获取{@link OssTemplate}
     *
     * @return top.jiaojinxin.jln.oss.OssTemplate
     * @author JiaoJinxin
     */
    public static OssTemplate getOssTemplate() {
        return OssManager.getOssTemplate();
    }

    // endregion
}
