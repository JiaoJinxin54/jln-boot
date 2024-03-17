package top.jiaojinxin.system.data.entity;

import com.mybatisflex.annotation.Column;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

import static top.jiaojinxin.system.data.constants.DataConstants.COLUMN_NAME_TENANT;

/**
 * 基础实体（含租户标识）
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
public abstract class BaseTenantEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 628109916935076379L;

    /**
     * 租户标识
     */
    @Column(value = COLUMN_NAME_TENANT, tenantId = true)
    private String tenantCode;
}
