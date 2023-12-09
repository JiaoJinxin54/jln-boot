package top.jiaojinxin.core.model.resp;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 分页响应对象
 *
 * @param <T> 列数据泛型
 * @author JiaoJinxin
 */
public class PaginationResp<T> extends SingletonResp<PaginationResp.PaginationResult<T>> {
    private static final long serialVersionUID = 6079348728718088253L;

    /**
     * 构造方法
     *
     * @param data 响应数据值
     * @author JiaoJinxin
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
     * @return top.jiaojinxin.core.model.resp.model.PageResp
     * @author JiaoJinxin
     */
    public static <T> PaginationResp<T> ok(long count, @NonNull T[] items) {
        return ok(new DefaultPaginationResult<>(count, Arrays.stream(items).collect(Collectors.toList())));
    }

    /**
     * 成功
     *
     * @param count 总数量
     * @param items 当前页数据
     * @param <T>   数据类型
     * @return top.jiaojinxin.core.model.resp.model.PageResp
     * @author JiaoJinxin
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
     * @return top.jiaojinxin.core.model.resp.model.PageResp
     * @author JiaoJinxin
     */
    public static <T, PR extends PaginationResult<T>> PaginationResp<T> ok(@NonNull PR pr) {
        return new PaginationResp<>(pr);
    }

    /**
     * 分页结果
     *
     * @author JiaoJinxin
     */
    public interface PaginationResult<T> extends Serializable {

        /**
         * 总数量
         *
         * @return java.lang.Long
         * @author JiaoJinxin
         */
        Long getCount();

        /**
         * 当前页数据
         *
         * @return T[]
         * @author JiaoJinxin
         */
        Collection<T> getItems();
    }

    /**
     * 默认的分页查询结果实体
     *
     * @author JiaoJinxin
     */
    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class DefaultPaginationResult<T> implements PaginationResult<T> {
        private static final long serialVersionUID = 4615251709158934907L;

        private final Long count;

        private final Collection<T> items;
    }
}
