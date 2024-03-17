package top.jiaojinxin.system.data.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import lombok.Getter;
import lombok.Setter;
import top.jiaojinxin.system.data.dict.YesNo;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import static top.jiaojinxin.system.data.constants.DataConstants.COLUMN_NAME_CREATE_AT;
import static top.jiaojinxin.system.data.constants.DataConstants.COLUMN_NAME_CREATE_BY;
import static top.jiaojinxin.system.data.constants.DataConstants.COLUMN_NAME_LOGIC_DELETE;
import static top.jiaojinxin.system.data.constants.DataConstants.COLUMN_NAME_UPDATE_AT;
import static top.jiaojinxin.system.data.constants.DataConstants.COLUMN_NAME_UPDATE_BY;
import static top.jiaojinxin.system.data.constants.DataConstants.COLUMN_NAME_VERSION;

/**
 * 基础实体
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
public abstract class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -7594893880291531085L;

    /**
     * 主键ID
     */
    @Id(keyType = KeyType.Auto)
    private Integer id;

    /**
     * 逻辑删除
     */
    @Column(value = COLUMN_NAME_LOGIC_DELETE, isLogicDelete = true)
    private YesNo logicDelete;

    /**
     * 版本号（乐观锁）
     */
    @Column(value = COLUMN_NAME_VERSION, version = true)
    private Integer version;

    /**
     * 创建时间
     */
    @Column(value = COLUMN_NAME_CREATE_AT)
    private LocalDateTime createAt;

    /**
     * 创建人
     */
    @Column(value = COLUMN_NAME_CREATE_BY)
    private String createBy;

    /**
     * 更新时间
     */
    @Column(value = COLUMN_NAME_UPDATE_AT)
    private LocalDateTime updateAt;

    /**
     * 更新人
     */
    @Column(value = COLUMN_NAME_UPDATE_BY)
    private String updateBy;
}
