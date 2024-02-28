package top.jiaojinxin.util;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import top.jiaojinxin.common.model.DTO;

import java.util.Optional;

/**
 * 用户工具类
 *
 * @author JiaoJinxin
 */
public class CurrUserUtil {

    private static final TransmittableThreadLocal<CurrUser> CURR_USER_THREAD_LOCAL = new TransmittableThreadLocal<>();

    private static final CurrUser CURR_USER = new DefaultCurrUser(StrUtil.EMPTY, StrUtil.EMPTY);

    private CurrUserUtil() {
    }

    /**
     * 获取当前租户标识
     *
     * @return java.lang.String
     */
    public static String getTenantCode() {
        return getOrDefault().getTenantCode();
    }

    /**
     * 获取租户标识（含当前租户标识及子租户标识）
     *
     * @return java.lang.String[]
     */
    public static String[] getTenantCodes() {
        return getOrDefault().getTenantCodes();
    }

    /**
     * 获取用户标识
     *
     * @return java.lang.String
     */
    public static String getUserCode() {
        return getOrDefault().getUserCode();
    }

    /**
     * 获取当前用户
     *
     * @param clazz 当前用户的类型
     * @return T
     */
    public static <T extends CurrUser> T get(Class<T> clazz) {
        CurrUser currUser = CURR_USER_THREAD_LOCAL.get();
        if (ObjUtil.isEmpty(currUser)) {
            return null;
        }
        if (clazz.isAssignableFrom(currUser.getClass())) {
            return clazz.cast(currUser);
        }
        return null;
    }

    /**
     * 设置当前用户
     *
     * @param currUser 当前用户
     */
    public static void set(CurrUser currUser) {
        CURR_USER_THREAD_LOCAL.set(currUser);
    }

    /**
     * 清除当前用户
     */
    public static void clear() {
        CURR_USER_THREAD_LOCAL.remove();
    }

    /**
     * 获取当前用户
     *
     * @return top.jiaojinxin.util.CurrUserUtil.CurrUser
     */
    private static CurrUser getOrDefault() {
        return Optional.ofNullable(get(CurrUser.class)).orElse(CURR_USER);
    }

    /**
     * 默认用户
     */
    private record DefaultCurrUser(String getUserCode, String getTenantCode) implements CurrUser {
    }

    /**
     * 当前用户
     *
     * @author JiaoJinxin
     */
    public interface CurrUser extends DTO {

        /**
         * 用户标识
         *
         * @return java.lang.String
         */
        String getUserCode();

        /**
         * 租户标识
         *
         * @return java.lang.String
         */
        String getTenantCode();

        /**
         * 租户标识（含当前租户标识及子租户标识，用于解决集团与子公司使用不同租户进行数据隔离的场景）
         *
         * @return java.lang.String[]
         */
        default String[] getTenantCodes() {
            return new String[]{getTenantCode()};
        }
    }
}