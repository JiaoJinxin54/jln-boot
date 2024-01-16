package top.jiaojinxin.core.model.query;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 加载更多传输对象
 *
 * @param <ID> ID泛型
 * @param <C>  条件泛型
 * @author JiaoJinxin
 */
@Getter
@Setter
public class LoadMoreQuery<ID, C> implements Query {
    @Serial
    private static final long serialVersionUID = -3023282128988111485L;

    /**
     * 加载数量，默认：10
     */
    private long loadSize = 10;

    /**
     * 上一个ID
     */
    private ID prev;

    /**
     * 条件
     */
    private C condition;

    /**
     * 加载更多传输对象
     */
    public LoadMoreQuery() {
    }
}
