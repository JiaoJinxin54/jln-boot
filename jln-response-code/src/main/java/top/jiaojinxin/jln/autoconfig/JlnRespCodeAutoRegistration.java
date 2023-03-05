package top.jiaojinxin.jln.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import top.jiaojinxin.jln.properties.JlnRespCodeProperties;
import top.jiaojinxin.jln.respcode.LocaleHolder;
import top.jiaojinxin.jln.respcode.RespCode;
import top.jiaojinxin.jln.respcode.RespCodeHolder;
import top.jiaojinxin.jln.util.RespCodeManager;

/**
 * 响应码自动装配
 *
 * @author JiaoJinxin
 */
@EnableConfigurationProperties(JlnRespCodeProperties.class)
public class JlnRespCodeAutoRegistration {

    /**
     * 将响应码相关配置注册到静态工具类中，以便在非Spring Bean场景中静态调用
     *
     * @param respCodeProperties 响应码相关配置
     * @author JiaoJinxin
     */
    @Autowired
    public void setJlnRespCodeProperties(JlnRespCodeProperties respCodeProperties) {
        RespCodeManager.setRespCodeProperties(respCodeProperties);
    }

    /**
     * 将地理、政治或文化区域提供者注册到静态工具类中，以便在非Spring Bean场景中静态调用
     *
     * @param localeHolder 地理、政治或文化区域提供者
     * @author JiaoJinxin
     */
    @Autowired(required = false)
    public void setLocaleSupplier(LocaleHolder localeHolder) {
        RespCodeManager.setLocaleHolder(localeHolder);
    }

    /**
     * 将{@link RespCode}提供者注册到静态工具类中，以便在非Spring Bean场景中静态调用
     *
     * @param respCodeHolder {@link RespCode}提供者
     * @author JiaoJinxin
     */
    @Autowired(required = false)
    public void setRespCodeSupplier(RespCodeHolder respCodeHolder) {
        RespCodeManager.setRespCodeHolder(respCodeHolder);
    }
}
