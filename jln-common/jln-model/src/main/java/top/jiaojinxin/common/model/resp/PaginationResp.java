package top.jiaojinxin.common.model.resp;

import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

/**
 * 分页响应对象
 *
 * @param <T> 列数据泛型
 * @author JiaoJinxin
 */
public class PaginationResp<T> extends SingletonResp<PaginationResp.PaginationResult<T>> {
    @Serial
    private static final long serialVersionUID = 6079348728718088253L;

    /**
     * 构造方法
     *
     * @param data 响应数据值
     */
    protected PaginationResp(@NonNull PaginationResp.PaginationResult<T> data) {
        super(data);
    }

    /**
     * 成功
     *
     * @param count 总数量
     * @param items 当前页数据
     * @param <T>   数据类型
     * @return top.jiaojinxin.common.model.resp.PaginationResp
     */
    public static <T> PaginationResp<T> ok(long count, @NonNull T[] items) {
        return ok(new DefaultPaginationResult<>(count, Arrays.stream(items).toList()));
    }

    /**
     * 成功
     *
     * @param count 总数量
     * @param items 当前页数据
     * @param <T>   数据类型
     * @return top.jiaojinxin.common.model.resp.PaginationResp
     */
    public static <T> PaginationResp<T> ok(long count, @NonNull Collection<T> items) {
        return ok(new DefaultPaginationResult<>(count, items));
    }

    /**
     * 成功
     *
     * @param pr   分页查询结果
     * @param <T>  数据类型
     * @param <PR> 分页查询响应体类型
     * @return top.jiaojinxin.common.model.resp.PaginationResp
     */
    public static <T, PR extends PaginationResult<T>> PaginationResp<T> ok(@NonNull PR pr) {
        return new PaginationResp<>(pr);
    }

    /**
     * 分页结果
     *
     * @param <T> 条件泛型
     */
    public interface PaginationResult<T> extends Serializable {

        /**
         * 总数量
         *
         * @return java.lang.Long
         */
        Long getCount();

        /**
         * 当前页数据
         *
         * @return T[]
         */
        Collection<T> getItems();
    }

    /**
     * 默认的分页查询结果实体
     *
     * @param getCount 总数量
     * @param getItems 当前页数据
     * @param <T>      分页查询结果泛型
     */
    private record DefaultPaginationResult<T>(Long getCount, Collection<T> getItems) implements PaginationResult<T> {
    }
}
