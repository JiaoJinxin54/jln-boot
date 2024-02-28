package top.jiaojinxin.common.model.query;

import top.jiaojinxin.common.model.DTO;

/**
 * 查询传输对象，应用场景特征：
 * <ul>
 *     <li>会有返回结果</li>
 *     <li>不会改变对象状态</li>
 *     <li>对系统没有副作用</li>
 * </ul>
 *
 * @author JiaoJinxin
 */
public interface Query extends DTO {
}
