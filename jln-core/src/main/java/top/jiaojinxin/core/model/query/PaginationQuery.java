package top.jiaojinxin.core.model.query;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页查询传输对象
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
public class PaginationQuery<C> implements Query {
    private static final long serialVersionUID = -560117683339123344L;

    /**
     * 页码，默认：1
     */
    private long pageNum = 1;

    /**
     * 每页数量，默认：10
     */
    private long pageSize = 10;

    /**
     * 条件
     */
    private C condition;
}
