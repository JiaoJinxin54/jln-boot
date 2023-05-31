package top.jiaojinxin.jln.model.query;

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
     * 不包含
     */
    NOT_IN,
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