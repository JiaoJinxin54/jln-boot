package top.jiaojinxin.core.model.resp;

import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

/**
 * 加载更多响应对象
 *
 * @author JiaoJinxin
 */
public class LoadMoreResp<ID, T> extends SingletonResp<LoadMoreResp.LoadMoreResult<ID, T>> {

    @Serial
    private static final long serialVersionUID = 4267812710327758665L;

    /**
     * 构造方法
     *
     * @param data 响应数据值
     * @author JiaoJinxin
     */
    private LoadMoreResp(@NonNull LoadMoreResult<ID, T> data) {
        super(data);
    }

    /**
     * 成功
     *
     * @param last  当前页数据最后一个ID，即items中最后一条数据的唯一标识
     * @param items 当前页数据
     * @return top.jiaojinxin.core.model.resp.LoadMoreResp<ID, T>
     * @author JiaoJinxin
     */
    public static <ID, T> LoadMoreResp<ID, T> ok(ID last, @NonNull T[] items) {
        return ok(new DefaultLoadMoreResult<>(last, Arrays.stream(items).toList()));
    }

    /**
     * 成功
     *
     * @param last  当前页数据最后一个ID，即items中最后一条数据的唯一标识
     * @param items 当前页数据
     * @return top.jiaojinxin.core.model.resp.LoadMoreResp<ID, T>
     * @author JiaoJinxin
     */
    public static <ID, T> LoadMoreResp<ID, T> ok(ID last, @NonNull Collection<T> items) {
        return ok(new DefaultLoadMoreResult<>(last, items));
    }

    /**
     * 成功
     *
     * @param data 加载更多结果
     * @return top.jiaojinxin.core.model.resp.LoadMoreResp
     * @author JiaoJinxin
     */
    public static <ID, T, LMR extends LoadMoreResult<ID, T>> LoadMoreResp<ID, T> ok(LMR data) {
        return new LoadMoreResp<>(data);
    }

    /**
     * 加载更多结果
     *
     * @author JiaoJinxin
     */
    public interface LoadMoreResult<ID, T> extends Serializable {

        /**
         * 当前页数据最后一个ID
         *
         * @return ID
         * @author JiaoJinxin
         */
        ID getLast();

        /**
         * 当前页数据
         *
         * @return T[]
         * @author JiaoJinxin
         */
        Collection<T> getItems();
    }

    /**
     * 默认的加载更多结果实体
     *
     * @author JiaoJinxin
     */
    private record DefaultLoadMoreResult<ID, T>(ID getLast, Collection<T> getItems) implements LoadMoreResult<ID, T> {
    }
}
