package top.jiaojinxin.jln.mp.bo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.CollectionUtils;
import top.jiaojinxin.jln.model.query.PageQuery;
import top.jiaojinxin.jln.mp.dao.IBaseDAO;
import top.jiaojinxin.jln.mp.model.BaseEntity;
import top.jiaojinxin.jln.model.query.ConditionItem;

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
    public <R> boolean existsBy(Map<SFunction<E, R>, R> propertyMap) {
        if (CollectionUtils.isEmpty(propertyMap)) {
            return false;
        }
        LambdaQueryChainWrapper<E> wrapper = lambdaQuery();
        propertyMap.forEach((key, val) -> {
            if (key == null) {
                return;
            }
            if (val == null) {
                wrapper.isNull(key);
            } else {
                wrapper.eq(key, val);
            }
        });
        return wrapper.exists();
    }

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
    public <C> IPage<E> page(PageQuery<C> pageQuery, Function<C, Map<SFunction<E, ?>, ConditionItem<?>>> conditionItemConvert) {
        Page<E> page = Page.of(pageQuery.getPageNum(), pageQuery.getPageSize(), true);
        // 若根据条件获取到的字典Map为空，则直接跳过
        Map<SFunction<E, ?>, ConditionItem<?>> columnMap = conditionItemConvert.apply(pageQuery.getCondition());
        if (CollectionUtils.isEmpty(columnMap)) {
            return lambdaQuery().page(page);
        }
        LambdaQueryChainWrapper<E> wrapper = lambdaQuery();
        columnMap.forEach((column, conditionItem) -> {
            if (conditionItem == null) {
                return;
            }
            // 匹配条件
            if (ObjectUtils.isNotNull(conditionItem.getValue())) {
                switch (conditionItem.getMatchType()) {
                    case LIKE -> wrapper.like(column, conditionItem.getValue());
                    case L_LIKE -> wrapper.likeLeft(column, conditionItem.getValue());
                    case R_LIKE -> wrapper.likeRight(column, conditionItem.getValue());
                    case IN -> {
                        if (conditionItem.getValue() instanceof Collection<?> collection) {
                            wrapper.in(column, collection);
                        } else if (conditionItem.getValue() instanceof Object[] objs) {
                            wrapper.in(column, objs);
                        }
                    }
                    case NOT_IN -> {
                        if (conditionItem.getValue() instanceof Collection<?> collection) {
                            wrapper.notIn(column, collection);
                        } else if (conditionItem.getValue() instanceof Object[] objs) {
                            wrapper.notIn(column, objs);
                        }
                    }
                    case GT -> wrapper.gt(column, conditionItem.getValue());
                    case GE -> wrapper.ge(column, conditionItem.getValue());
                    case LT -> wrapper.lt(column, conditionItem.getValue());
                    case LE -> wrapper.le(column, conditionItem.getValue());
                    case NOT_EQUALS -> wrapper.ne(column, conditionItem.getValue());
                    case EQUALS -> wrapper.eq(column, conditionItem.getValue());
                }
            }
            // 排序条件
            switch (conditionItem.getOrderType()) {
                case DESC -> wrapper.orderByDesc(column);
                case ASC -> wrapper.orderByAsc(column);
            }
        });
        return wrapper.page(page);
    }
}
