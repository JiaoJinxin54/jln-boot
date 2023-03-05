package top.jiaojinxin.jln.mp.model;

/**
 * MybatisPlus模块当前登录用户模型
 *
 * @author JiaoJinxin
 */
public interface MpCurrUser {

    /**
     * 用户唯一标识
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    String getUid();

    /**
     * 租户唯一标识
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    String getTenant();
}
