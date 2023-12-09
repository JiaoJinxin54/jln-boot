package top.jiaojinxin.core.model.command;

import top.jiaojinxin.core.model.DTO;

/**
 * 命令传输对象，应用场景特征：
 * <ul>
 *     <li>不返回任何结果(void)</li>
 *     <li>会改变对象状态</li>
 * </ul>
 *
 * @author JiaoJinxin
 */
public interface Command extends DTO {
}
