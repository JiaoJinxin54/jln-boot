package top.jiaojinxin.jln.mp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.jiaojinxin.jln.mp.model.BaseEntity;

/**
 * <p>DAO基类</p>
 * <p>继承自{@link BaseMapper}</p>
 * <p>可扩展基础DAO层操作</p>
 *
 * @param <E> 实体类型
 * @author JiaoJinxin
 */
public interface IBaseDAO<E extends BaseEntity> extends BaseMapper<E> {
}
