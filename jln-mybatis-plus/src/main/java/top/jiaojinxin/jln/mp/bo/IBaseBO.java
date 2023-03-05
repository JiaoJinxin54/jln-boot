package top.jiaojinxin.jln.mp.bo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.util.CollectionUtils;
import top.jiaojinxin.jln.model.query.PageCondition;
import top.jiaojinxin.jln.model.query.PageQuery;
import top.jiaojinxin.jln.mp.model.BaseEntity;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>单表业务操作接口基类</p>
 * <p>继承自{@link IService}</p>
 * <p>可扩展基础的单表业务操作</p>
 *
 * @param <E> 实体类型
 * @author JiaoJinxin
 */
public interface IBaseBO<E extends BaseEntity> extends IService<E> {

    /**
     * 删除（根据指定属性获取符合条件的所有对象）
     *
     * @param property      属性
     * @param propertyValue 属性值
     * @param <R>           属性类型
     * @return boolean
     * @author JiaoJinxin
     */
    default <R> boolean removeBy(SFunction<E, R> property, R propertyValue) {
        List<Integer> ids = listBy(property, propertyValue).stream().map(BaseEntity::getId).toList();
        if (CollectionUtils.isEmpty(ids)) {
            return false;
        }
        return removeByIds(ids);
    }

    /**
     * 单个查询（根据指定属性获取符合条件的任意一个对象）
     *
     * @param property      属性
     * @param propertyValue 属性值
     * @param <R>           属性类型
     * @return E
     * @author JiaoJinxin
     */
    default <R> E getBy(SFunction<E, R> property, R propertyValue) {
        return getBy(property, propertyValue, Function.identity());
    }

    /**
     * 单个查询（根据指定属性获取符合条件的任意一个对象）
     *
     * @param property      属性
     * @param propertyValue 属性值
     * @param convertor     结果转换器
     * @param <R>           属性类型
     * @param <F>           转换后的最终结果
     * @return E
     * @author JiaoJinxin
     */
    default <R, F> F getBy(SFunction<E, R> property, R propertyValue, Function<E, F> convertor) {
        return listBy(property, propertyValue, convertor).stream().findAny().orElse(null);
    }

    /**
     * 列表查询（根据主键ID）
     *
     * @param ids       主键ID集合
     * @param convertor 结果转换器
     * @param <F>       转换后的最终结果
     * @return java.util.Collection
     * @author JiaoJinxin
     */
    <F> Collection<F> listByIds(Collection<Integer> ids, Function<E, F> convertor);

    /**
     * 列表查询（根据指定属性获取符合条件的所有对象）
     *
     * @param property      属性
     * @param propertyValue 属性值
     * @param <R>           属性类型
     * @return java.util.Collection
     * @author JiaoJinxin
     */
    default <R> Collection<E> listBy(SFunction<E, R> property, R propertyValue) {
        return listBy(property, propertyValue, Function.identity());
    }

    /**
     * 列表查询（根据指定属性获取符合条件的所有对象）
     *
     * @param property      属性
     * @param propertyValue 属性值
     * @param convertor     结果转换器
     * @param <R>           属性类型
     * @param <F>           转换后的最终结果
     * @return E
     * @author JiaoJinxin
     */
    <R, F> Collection<F> listBy(SFunction<E, R> property, R propertyValue, Function<E, F> convertor);

    /**
     * 分页查询
     *
     * @param pageQuery            分页查询数据传输对象
     * @param conditionItemConvert 转换器：将分页查询条件{@link PageCondition}转换为{@link PageCondition.ConditionItem}
     * @param <C>                  分页查询条件数据传输对象
     * @return com.baomidou.mybatisplus.core.metadata.IPage
     * @author JiaoJinxin
     */
    <C extends PageCondition> IPage<E> page(PageQuery<C> pageQuery, Function<C, Map<SFunction<E, ?>, PageCondition.ConditionItem<?>>> conditionItemConvert);

    /**
     * 分页查询
     *
     * @param pageQuery            分页查询数据传输对象
     * @param entityConvert        转换器：将分页查询结果{@link E}转换为{@link F}
     * @param conditionItemConvert 转换器：将分页查询条件{@link PageCondition}转换为{@link PageCondition.ConditionItem}
     * @param <C>                  分页查询条件数据传输对象
     * @param <F>                  分页查询实际返回结果
     * @return com.baomidou.mybatisplus.core.metadata.IPage
     * @author JiaoJinxin
     */
    default <C extends PageCondition, F> IPage<F> page(PageQuery<C> pageQuery, Function<E, F> entityConvert,
                                                       Function<C, Map<SFunction<E, ?>, PageCondition.ConditionItem<?>>> conditionItemConvert) {
        return page(pageQuery, conditionItemConvert).convert(entityConvert);
    }
}
