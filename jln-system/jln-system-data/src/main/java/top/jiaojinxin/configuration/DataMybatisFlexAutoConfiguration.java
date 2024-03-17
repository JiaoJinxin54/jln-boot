package top.jiaojinxin.configuration;

import com.mybatisflex.annotation.InsertListener;
import com.mybatisflex.annotation.UpdateListener;
import com.mybatisflex.core.tenant.TenantFactory;
import com.mybatisflex.spring.boot.MyBatisFlexCustomizer;
import com.mybatisflex.spring.boot.MybatisFlexAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import top.jiaojinxin.system.data.entity.BaseEntity;
import top.jiaojinxin.util.CurrUserUtil;
import top.jiaojinxin.system.data.dict.YesNo;

import java.time.LocalDateTime;

/**
 * MybatisFlex相关全局配置
 *
 * @author JiaoJinxin
 */
@AutoConfigureBefore(MybatisFlexAutoConfiguration.class)
public class DataMybatisFlexAutoConfiguration {

    /**
     * 租户工厂
     *
     * @return com.mybatisflex.core.tenant.TenantFactory
     */
    @Bean
    public TenantFactory tenantFactory() {
        return CurrUserUtil::getTenantCodes;
    }

    /**
     * MyBatisFlex全局配置自定义修改
     *
     * @return com.mybatisflex.spring.boot.MyBatisFlexCustomizer
     */
    @Bean
    public MyBatisFlexCustomizer jlnMyBatisFlexCustomizer() {
        return flexGlobalConfig -> {
            flexGlobalConfig.registerInsertListener(insertListener(), BaseEntity.class);
            flexGlobalConfig.registerUpdateListener(updateListener(), BaseEntity.class);
            flexGlobalConfig.setNormalValueOfLogicDelete(YesNo.NO);
            flexGlobalConfig.setDeletedValueOfLogicDelete(YesNo.YES);
        };
    }

    /**
     * 默认{@link InsertListener}实现
     *
     * @return com.mybatisflex.annotation.InsertListener
     */
    private InsertListener insertListener() {
        return entity -> {
            if (entity instanceof BaseEntity baseEntity) {
                LocalDateTime now = LocalDateTime.now();
                String userId = CurrUserUtil.getUserCode();
                baseEntity.setCreateAt(now);
                baseEntity.setCreateBy(userId);
                baseEntity.setUpdateAt(now);
                baseEntity.setUpdateBy(userId);
            }
        };
    }

    /**
     * 默认{@link UpdateListener}实现
     *
     * @return com.mybatisflex.annotation.UpdateListener
     */
    private UpdateListener updateListener() {
        return entity -> {
            if (entity instanceof BaseEntity baseEntity) {
                baseEntity.setUpdateAt(LocalDateTime.now());
                baseEntity.setUpdateBy(CurrUserUtil.getUserCode());
            }
        };
    }
}
