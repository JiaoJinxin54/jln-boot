package top.jiaojinxin.log.audit.model;

import java.io.Serializable;

/**
 * 日志详情
 *
 * @author JiaoJinxin
 */
public interface LogDetails extends Serializable {

    /**
     * 操作
     *
     * @return java.lang.String
     */
    String getOperation();

    /**
     * 类型
     *
     * @return java.lang.String
     */
    String getType();
}
