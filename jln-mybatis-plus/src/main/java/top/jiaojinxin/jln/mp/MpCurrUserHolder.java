package top.jiaojinxin.jln.mp;

import top.jiaojinxin.jln.mp.model.MpCurrUser;

/**
 * 当前用户持有者
 *
 * @author JiaoJinxin
 */
@FunctionalInterface
public interface MpCurrUserHolder<T extends MpCurrUser> {

    /**
     * 获取当前用户
     *
     * @return T
     * @author JiaoJinxin
     */
    T getCurrUser();
}
