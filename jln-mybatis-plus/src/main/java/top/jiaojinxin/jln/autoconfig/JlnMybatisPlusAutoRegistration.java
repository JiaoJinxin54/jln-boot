package top.jiaojinxin.jln.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;
import top.jiaojinxin.jln.mp.MpCurrUserHolder;
import top.jiaojinxin.jln.mp.model.MpCurrUser;
import top.jiaojinxin.jln.properties.JlnMpProperties;
import top.jiaojinxin.jln.util.MpManager;

/**
 * Mybatis-Plus相关公共配置
 *
 * @author JiaoJinxin
 */
public class JlnMybatisPlusAutoRegistration {

    /**
     * 将MybatisPlus相关配置注册到静态工具类中，以便在非Spring Bean场景中静态调用
     *
     * @param mpProperties MybatisPlus相关配置
     * @author JiaoJinxin
     */
    @Autowired
    public void setJlnMpProperties(JlnMpProperties mpProperties) {
        MpManager.setMpProperties(mpProperties);
    }

    /**
     * 将当前用户提供者注册到静态工具类中，以便在非Spring Bean场景中静态调用
     *
     * @param currUserSupplier 当前用户提供者
     * @author JiaoJinxin
     */
    @Autowired(required = false)
    public void setMpCurrUserSupplier(MpCurrUserHolder<? extends MpCurrUser> currUserSupplier) {
        MpManager.setCurrUserHolder(currUserSupplier);
    }

    /**
     * 将事务模板注册到静态工具类中，以便在非Spring Bean场景中静态调用
     *
     * @param transactionTemplate 事务模板
     * @author JiaoJinxin
     */
    @Autowired
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        MpManager.setTransactionTemplate(transactionTemplate);
    }
}
