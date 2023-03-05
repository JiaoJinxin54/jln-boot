package top.jiaojinxin.jln.util;

import org.springframework.transaction.support.TransactionTemplate;
import top.jiaojinxin.jln.mp.MpCurrUserHolder;
import top.jiaojinxin.jln.mp.model.MpCurrUser;
import top.jiaojinxin.jln.properties.JlnMpProperties;

/**
 * Mybatis-Plus管理类
 *
 * @author JiaoJinxin
 */
public class MpManager {

    private MpManager() {
    }

    /**
     * Mybatis-Plus需要配置的参数
     */
    private static JlnMpProperties mpProperties;

    /**
     * 获取{@link MpManager#mpProperties}
     *
     * @return top.jiaojinxin.jln.properties.JlnMpProperties
     * @author JiaoJinxin
     */
    public static JlnMpProperties getMpProperties() {
        return mpProperties;
    }

    /**
     * 设置{@link MpManager#mpProperties}
     *
     * @param mpProperties {@link MpManager#mpProperties}
     * @author JiaoJinxin
     */
    public static void setMpProperties(JlnMpProperties mpProperties) {
        MpManager.mpProperties = mpProperties;
    }

    /**
     * 当前用户提供者
     */
    private static volatile MpCurrUserHolder<? extends MpCurrUser> currUserHolder;

    /**
     * 获取{@link MpManager#currUserHolder}
     *
     * @return top.jiaojinxin.jln.mp.MpCurrUserSupplier
     * @author JiaoJinxin
     */
    public static MpCurrUserHolder<? extends MpCurrUser> getCurrUserHolder() {
        if (currUserHolder == null) {
            synchronized (MpManager.class) {
                if (currUserHolder == null) {
                    setCurrUserHolder(() -> new MpCurrUser() {
                        @Override
                        public String getUid() {
                            return "-1";
                        }

                        @Override
                        public String getTenant() {
                            return "-1";
                        }
                    });
                }
            }
        }
        return currUserHolder;
    }

    /**
     * 设置{@link MpManager#currUserHolder}
     *
     * @param currUserHolder {@link MpManager#currUserHolder}
     * @author JiaoJinxin
     */
    public static void setCurrUserHolder(MpCurrUserHolder<? extends MpCurrUser> currUserHolder) {
        MpManager.currUserHolder = currUserHolder;
    }

    /**
     * 事务模板
     */
    private static TransactionTemplate transactionTemplate;

    /**
     * 获取{@link MpManager#transactionTemplate}
     *
     * @return org.springframework.transaction.support.TransactionTemplate
     * @author JiaoJinxin
     */
    public static TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    /**
     * 设置{@link MpManager#transactionTemplate}
     *
     * @param transactionTemplate {@link MpManager#transactionTemplate}
     * @author JiaoJinxin
     */
    public static void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        MpManager.transactionTemplate = transactionTemplate;
    }
}
