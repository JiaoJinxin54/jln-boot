package top.jiaojinxin.jln.model.query;

import java.io.Serial;

/**
 * 分页查询传输对象
 *
 * @author JiaoJinxin
 */
public class PageQuery<C extends PageCondition> implements Query {

    @Serial
    private static final long serialVersionUID = -560117683339123344L;

    /**
     * 页码
     */
    private long pageNum = 1;

    /**
     * 每页数量
     */
    private long pageSize = 10;

    /**
     * 条件
     */
    private C condition;

    /**
     * 获取{@link PageQuery#pageNum}
     *
     * @return long
     * @author JiaoJinxin
     */
    public long getPageNum() {
        return pageNum;
    }

    /**
     * 设置{@link PageQuery#pageNum}
     *
     * @param pageNum {@link PageQuery#pageNum}
     * @author JiaoJinxin
     */
    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * 获取{@link PageQuery#pageSize}
     *
     * @return long
     * @author JiaoJinxin
     */
    public long getPageSize() {
        return pageSize;
    }

    /**
     * 设置{@link PageQuery#pageSize}
     *
     * @param pageSize {@link PageQuery#pageSize}
     * @author JiaoJinxin
     */
    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取{@link PageQuery#condition}
     *
     * @return C
     * @author JiaoJinxin
     */
    public C getCondition() {
        return condition;
    }

    /**
     * 设置{@link PageQuery#condition}
     *
     * @param condition {@link PageQuery#condition}
     * @author JiaoJinxin
     */
    public void setCondition(C condition) {
        this.condition = condition;
    }
}
