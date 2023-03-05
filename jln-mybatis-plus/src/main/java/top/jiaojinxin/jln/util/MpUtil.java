package top.jiaojinxin.jln.util;

/**
 * Mybatis-Plus工具类
 *
 * @author JiaoJinxin
 */
public class MpUtil {
    private MpUtil() {
    }

    /**
     * 开启事务执行某段程序
     *
     * @param runnable 需要开启事务运行的程序
     * @author JiaoJinxin
     */
    public static void executeTransaction(Runnable runnable) {
        MpManager.getTransactionTemplate().executeWithoutResult(transactionStatus -> runnable.run());
    }
}
