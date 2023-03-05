package top.jiaojinxin.jln.model.query;

import java.io.Serial;
import java.io.Serializable;

/**
 * 分页查询条件传输对象<br/>
 * 属性类型均为{@link ConditionItem}
 *
 * @author JiaoJinxin
 */
public class PageCondition implements Serializable {

    @Serial
    private static final long serialVersionUID = -8851820731618536110L;

    /**
     * 分页查询条件项
     *
     * @author JiaoJinxin
     */
    public static class ConditionItem<T> {

        /**
         * 当前条件值
         */
        private T value;

        /**
         * 匹配类型
         */
        private MatchType matchType;

        /**
         * 排序类型
         */
        private OrderType orderType;

        public ConditionItem() {
            this.matchType = MatchType.EQUALS;
            this.orderType = OrderType.NONE;
        }

        public ConditionItem(T value) {
            this.value = value;
            this.matchType = MatchType.EQUALS;
            this.orderType = OrderType.NONE;
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

    /**
     * 匹配类型枚举
     *
     * @author JiaoJinxin
     */
    public enum MatchType {
        /**
         * 精确匹配
         */
        EQUALS,
        /**
         * 不匹配
         */
        NOT_EQUALS,
        /**
         * 模糊匹配：包含该值
         */
        LIKE,
        /**
         * 模糊匹配：以该值结尾
         */
        L_LIKE,
        /**
         * 模糊匹配：以该值开始
         */
        R_LIKE,
        /**
         * 包含
         */
        IN,
        /**
         * 大于
         */
        GT,
        /**
         * 大于等于
         */
        GE,
        /**
         * 小于
         */
        LT,
        /**
         * 小于等于
         */
        LE,
    }

    /**
     * 排序模式枚举
     *
     * @author JiaoJinxin
     */
    public enum OrderType {
        /**
         * 不排序
         */
        NONE,
        /**
         * 正序
         */
        ASC,
        /**
         * 倒序
         */
        DESC
    }
}
