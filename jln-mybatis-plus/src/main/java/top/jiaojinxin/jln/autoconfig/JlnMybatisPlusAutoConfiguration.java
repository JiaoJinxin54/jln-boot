package top.jiaojinxin.jln.autoconfig;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.IllegalSQLInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.ReplacePlaceholderInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import top.jiaojinxin.jln.mp.model.BaseEntity;
import top.jiaojinxin.jln.mp.model.MpCurrUser;
import top.jiaojinxin.jln.properties.JlnMpProperties;
import top.jiaojinxin.jln.util.MpManager;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

/**
 * Mybatis-Plus相关公共配置
 *
 * @author JiaoJinxin
 */
@EnableConfigurationProperties({JlnMpProperties.class})
public class JlnMybatisPlusAutoConfiguration {

    /**
     * 字段自动填充策略
     *
     * @return com.baomidou.mybatisplus.core.handlers.MetaObjectHandler
     * @author JiaoJinxin
     */
    @Bean
    @ConditionalOnMissingBean(MetaObjectHandler.class)
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                LocalDateTime now = LocalDateTime.now();
                MpCurrUser currUser = MpManager.getCurrUserHolder().getCurrUser();
                Object tenantUID = currUser.getTenant();
                Object userUID = currUser.getUid();
                insertFill(metaObject, now, tenantUID, userUID);
                updateFill(metaObject, now, userUID);
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                LocalDateTime now = LocalDateTime.now();
                Object userUID = MpManager.getCurrUserHolder().getCurrUser().getUid();
                updateFill(metaObject, now, userUID);
            }

            private void insertFill(MetaObject metaObject, LocalDateTime localDateTime, Object tenantUID, Object userUID) {
                fillStrategy(metaObject, BaseEntity.Fields.tenant, tenantUID);
                fillStrategy(metaObject, BaseEntity.Fields.createBy, userUID);
                fillStrategy(metaObject, BaseEntity.Fields.createAt, localDateTime);
            }

            private void updateFill(MetaObject metaObject, LocalDateTime localDateTime, Object userUID) {
                fillStrategy(metaObject, BaseEntity.Fields.updateBy, userUID);
                fillStrategy(metaObject, BaseEntity.Fields.updateAt, localDateTime);
            }
        };
    }

    /**
     * MybatisPlus注册拦截器
     *
     * @param mpProperties MybatisPlus相关配置
     * @return com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
     * @author JiaoJinxin
     */
    @Bean
    @ConditionalOnMissingBean(MybatisPlusInterceptor.class)
    public MybatisPlusInterceptor mybatisPlusInterceptor(JlnMpProperties mpProperties) {
        MybatisPlusInterceptor mpi = new MybatisPlusInterceptor();
        // 插件：分页
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setOverflow(mpProperties.isOverflow());
        paginationInnerInterceptor.setMaxLimit(mpProperties.getMaxLimit());
        paginationInnerInterceptor.setDbType(mpProperties.getDbType());
        mpi.addInnerInterceptor(paginationInnerInterceptor);
        // 插件：乐观锁
        mpi.addInnerInterceptor(new OptimisticLockerInnerInterceptor(true));
        // 插件：多租户
        mpi.addInnerInterceptor(new TenantLineInnerInterceptor(tenantLineHandler()));
        // 插件：防止全表更新与删除
        if (mpProperties.isBlockAttack()) {
            mpi.addInnerInterceptor(new BlockAttackInnerInterceptor());
        }
        // 插件：数据权限处理
        if (mpProperties.isDataPermission()) {
            mpi.addInnerInterceptor(new DataPermissionInterceptor());
        }
        // 插件：动态表名
        if (mpProperties.isDynamicTableName()) {
            mpi.addInnerInterceptor(new DynamicTableNameInnerInterceptor());
        }
        // 插件：垃圾SQL拦截
        if (mpProperties.isIllegalSQL()) {
            mpi.addInnerInterceptor(new IllegalSQLInnerInterceptor());
        }
        // 插件；占位符替换
        if (mpProperties.isReplacePlaceholder()) {
            if (mpProperties.getEscapeSymbol() == null || mpProperties.getEscapeSymbol().isBlank()) {
                mpi.addInnerInterceptor(new ReplacePlaceholderInnerInterceptor());
            } else {
                mpi.addInnerInterceptor(new ReplacePlaceholderInnerInterceptor(mpProperties.getEscapeSymbol()));
            }
        }
        return mpi;
    }

    /**
     * 多租户处理器
     *
     * @return com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler
     * @author JiaoJinxin
     */
    private TenantLineHandler tenantLineHandler() {
        return new TenantLineHandler() {
            @Override
            public Expression getTenantId() {
                return new StringValue(MpManager.getCurrUserHolder().getCurrUser().getTenant());
            }

            @Override
            public String getTenantIdColumn() {
                return BaseEntity.TENANT;
            }

            @Override
            public boolean ignoreTable(String tableName) {
                return Arrays.asList(Optional.ofNullable(MpManager.getMpProperties().getIgnoreTenantTables()).orElse(new String[0])).contains(tableName);
            }
        };
    }
}
