package top.jiaojinxin.jln.mp.bo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.jiaojinxin.jln.model.query.PageCondition;
import top.jiaojinxin.jln.model.query.PageQuery;
import top.jiaojinxin.jln.mp.dao.IBaseDAO;
import top.jiaojinxin.jln.mp.model.BaseEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>单表业务操作实现类基类</p>
 * <p>继承自{@link ServiceImpl}</p>
 * <p>可扩展基础的单表业务操作</p>
 *
 * @param <E> 实体类型
 * @param <D> DAO
 * @author JiaoJinxin
 */
public abstract class BaseBOImpl<E extends BaseEntity, D extends IBaseDAO<E>> extends ServiceImpl<D, E> implements IBaseBO<E> {

    @Override
    public <F> Collection<F> listByIds(Collection<Integer> ids, Function<E, F> convertor) {
        return listByIds(ids).stream().map(convertor).toList();
    }

    @Override
    public <R, F> Collection<F> listBy(SFunction<E, R> property, R propertyValue, Function<E, F> convertor) {
        if (propertyValue == null) {
            return Collections.emptyList();
        }
        return lambdaQuery().eq(property, propertyValue).list().stream().map(convertor).toList();
    }

    @Override
    public <C extends PageCondition> IPage<E> page(PageQuery<C> pageQuery, Function<C, Map<SFunction<E, ?>, PageCondition.ConditionItem<?>>> conditionItemConvert) {
        Page<E> page = Page.of(pageQuery.getPageNum(), pageQuery.getPageSize(), true);
        C condition = pageQuery.getCondition();
        if (null == condition) {
            return lambdaQuery().orderByDesc(BaseEntity::getCreateAt).page(page);
        }
        LambdaQueryChainWrapper<E> wrapper = lambdaQuery();
        conditionItemConvert.apply(condition).forEach((k, v) -> {
            if (v == null || v.getValue() == null) {
                return;
            }
            switch (v.getMatchType()) {
                case LIKE -> wrapper.like(k, v.getValue());
                case L_LIKE -> wrapper.likeLeft(k, v.getValue());
                case R_LIKE -> wrapper.likeRight(k, v.getValue());
                case IN -> wrapper.in(k, v.getValue());
                case GT -> wrapper.gt(k, v.getValue());
                case GE -> wrapper.ge(k, v.getValue());
                case LT -> wrapper.lt(k, v.getValue());
                case LE -> wrapper.le(k, v.getValue());
                case NOT_EQUALS -> wrapper.ne(k, v.getValue());
                default -> wrapper.eq(k, v.getValue());
            }
            switch (v.getOrderType()) {
                case DESC -> wrapper.orderByDesc(k);
                case ASC -> wrapper.orderByAsc(k);
                default -> {
                }
            }
        });
        return wrapper.orderByDesc(BaseEntity::getCreateAt).page(page);
    }
}
