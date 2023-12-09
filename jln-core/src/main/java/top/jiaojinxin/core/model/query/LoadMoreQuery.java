package top.jiaojinxin.core.model.query;

import lombok.Getter;
import lombok.Setter;

/**
 * 加载更多传输对象
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
public class LoadMoreQuery<ID, C> implements Query {
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
}
