package top.jiaojinxin.jln.mp.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entity基类
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 7688027818548671638L;

    /**
     * 主键字段值
     */
    public static final String ID = "id";

    /**
     * 租户字段值
     */
    public static final String TENANT = "tenant";

    /**
     * 创建人字段值
     */
    public static final String CREATE_BY = "create_by";

    /**
     * 创建时间字段值
     */
    public static final String CREATE_AT = "create_at";

    /**
     * 更新人字段值
     */
    public static final String UPDATE_BY = "update_by";

    /**
     * 更新时间字段值
     */
    public static final String UPDATE_AT = "update_at";

    /**
     * 版本号字段值
     */
    public static final String VERSION = "version";

    /**
     * 删除标识字段值
     */
    public static final String DEL_FLAG = "del_flag";

    /**
     * 主键ID
     */
    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    /**
     * 租户
     */
    @TableField(value = TENANT, fill = FieldFill.INSERT)
    private String tenant;

    /**
     * 创建人
     */
    @TableField(value = CREATE_BY, fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = CREATE_AT, fill = FieldFill.INSERT)
    private LocalDateTime createAt;

    /**
     * 更新人
     */
    @TableField(value = UPDATE_BY, fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = UPDATE_AT, fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateAt;

    /**
     * 版本号
     */
    @Version
    @TableField(value = VERSION)
    private Integer version;

    /**
     * 删除标识
     */
    @TableLogic
    @TableField(value = DEL_FLAG)
    private Byte delFlag;
}
