package top.jiaojinxin.jln.model.resp;

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
public class PageResp<T> extends SingletonResp<PageResp.PageResult<T>> {

    @Serial
    private static final long serialVersionUID = 6079348728718088253L;

    /**
     * 构造方法
     *
     * @param data 响应数据值
     * @author JiaoJinxin
     */
    protected PageResp(PageResp.PageResult<T> data) {
        super(data);
    }

    /**
     * 成功
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @param count    总数量
     * @param items    当前页数据
     * @param <T>      数据类型
     * @return top.jiaojinxin.jln.model.resp.PageResp
     * @author JiaoJinxin
     */
    public static <T> PageResp<T> ok(Long pageNum, Long pageSize, Long count, T[] items) {
        return new PageResp<>(new DefaultPageResult<>(Arrays.stream(items).toList(), count, pageNum, pageSize));
    }

    /**
     * 成功
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @param count    总数量
     * @param items    当前页数据
     * @param <T>      数据类型
     * @return top.jiaojinxin.jln.model.resp.PageResp
     * @author JiaoJinxin
     */
    public static <T> PageResp<T> ok(Long pageNum, Long pageSize, Long count, Collection<T> items) {
        return new PageResp<>(new DefaultPageResult<>(items, count, pageNum, pageSize));
    }

    /**
     * 成功
     *
     * @param pr   分页查询结果
     * @param <T>  数据类型
     * @param <PR> 分页查询响应体类型
     * @return top.jiaojinxin.jln.model.resp.PageResp
     * @author JiaoJinxin
     */
    public static <T, PR extends PageResp.PageResult<T>> PageResp<T> ok(PR pr) {
        return new PageResp<>(pr);
    }

    /**
     * 分页结果
     *
     * @author JiaoJinxin
     */
    public interface PageResult<T> extends Serializable {

        /**
         * 当前页数据
         *
         * @return T[]
         * @author JiaoJinxin
         */
        Collection<T> getItems();

        /**
         * 总数量
         *
         * @return java.lang.Long
         * @author JiaoJinxin
         */
        Long getCount();

        /**
         * 页码
         *
         * @return java.lang.Long
         * @author JiaoJinxin
         */
        Long getPageNum();

        /**
         * 每页数量
         *
         * @return java.lang.Long
         * @author JiaoJinxin
         */
        Long getPageSize();
    }

    /**
     * 默认的分页查询结果实体
     *
     * @param items    当前页数据
     * @param count    总数量
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @author JiaoJinxin
     */
    private record DefaultPageResult<T>(Collection<T> items, Long count, Long pageNum,
                                        Long pageSize) implements PageResult<T> {
        @Override
        public Collection<T> getItems() {
            return this.items;
        }

        @Override
        public Long getCount() {
            return this.count;
        }

        @Override
        public Long getPageNum() {
            return this.pageNum;
        }

        @Override
        public Long getPageSize() {
            return this.pageSize;
        }
    }
}
