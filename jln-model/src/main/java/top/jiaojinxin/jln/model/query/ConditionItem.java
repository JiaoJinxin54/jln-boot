package top.jiaojinxin.jln.model.query;

/**
 * 分页查询条件项
 *
 * @author JiaoJinxin
 */
public class ConditionItem<T> {

    /**
     * 当前条件值
     */
    private T value;

    /**
     * 匹配类型
     */
    private MatchType matchType = MatchType.EQUALS;

    /**
     * 排序类型
     */
    private OrderType orderType = OrderType.NONE;

    /**
     * 构造分页条件
     *
     * @param value 当前条件值
     * @param <T>   数据类型
     * @return top.jiaojinxin.jln.model.query.ConditionItem
     * @author JiaoJinxin
     */
    public static <T> ConditionItem<T> of(T value) {
        return new ConditionItem<T>().setValue(value);
    }

    /**
     * 构造分页条件
     *
     * @param value     当前条件值
     * @param matchType 匹配类型
     * @param <T>       数据类型
     * @return top.jiaojinxin.jln.model.query.ConditionItem
     * @author JiaoJinxin
     */
    public static <T> ConditionItem<T> of(T value, MatchType matchType) {
        return new ConditionItem<T>().setValue(value).setMatchType(matchType);
    }

    /**
     * 构造分页条件
     *
     * @param value     当前条件值
     * @param orderType 排序类型
     * @param <T>       数据类型
     * @return top.jiaojinxin.jln.model.query.ConditionItem
     * @author JiaoJinxin
     */
    public static <T> ConditionItem<T> of(T value, OrderType orderType) {
        return new ConditionItem<T>().setValue(value).setOrderType(orderType);
    }

    /**
     * 构造分页条件
     *
     * @param orderType 排序类型
     * @param <T>       数据类型
     * @return top.jiaojinxin.jln.model.query.ConditionItem
     * @author JiaoJinxin
     */
    public static <T> ConditionItem<T> of(OrderType orderType) {
        return new ConditionItem<T>().setOrderType(orderType);
    }

    /**
     * 构造分页条件
     *
     * @param value     当前条件值
     * @param matchType 匹配类型
     * @param orderType 排序类型
     * @param <T>       数据类型
     * @return top.jiaojinxin.jln.model.query.ConditionItem
     * @author JiaoJinxin
     */
    public static <T> ConditionItem<T> of(T value, MatchType matchType, OrderType orderType) {
        return new ConditionItem<T>().setValue(value).setMatchType(matchType).setOrderType(orderType);
    }

    /**
     * 获取{@link ConditionItem#value}
     *
     * @return T
     * @author JiaoJinxin
     */
    public T getValue() {
        return value;
    }

    /**
     * 设置{@link ConditionItem#value}
     *
     * @param value {@link ConditionItem#value}
     * @return top.jiaojinxin.jln.model.query.PageCondition.ConditionItem
     * @author JiaoJinxin
     */
    public ConditionItem<T> setValue(T value) {
        this.value = value;
        return this;
    }

    /**
     * 获取{@link ConditionItem#matchType}
     *
     * @return top.jiaojinxin.jln.model.query.PageCondition.MatchType
     * @author JiaoJinxin
     */
    public MatchType getMatchType() {
        return matchType;
    }

    /**
     * 设置{@link ConditionItem#matchType}
     *
     * @param matchType {@link ConditionItem#matchType}
     * @return top.jiaojinxin.jln.model.query.PageCondition.ConditionItem
     * @author JiaoJinxin
     */
    public ConditionItem<T> setMatchType(MatchType matchType) {
        this.matchType = matchType;
        return this;
    }

    /**
     * 获取{@link ConditionItem#orderType}
     *
     * @return top.jiaojinxin.jln.model.query.PageCondition.OrderType
     * @author JiaoJinxin
     */
    public OrderType getOrderType() {
        return orderType;
    }

    /**
     * 设置{@link ConditionItem#orderType}
     *
     * @param orderType {@link ConditionItem#orderType}
     * @return top.jiaojinxin.jln.model.query.PageCondition.ConditionItem
     * @author JiaoJinxin
     */
    public ConditionItem<T> setOrderType(OrderType orderType) {
        this.orderType = orderType;
        return this;
    }
}