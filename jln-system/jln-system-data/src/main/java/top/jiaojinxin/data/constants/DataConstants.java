package top.jiaojinxin.data.constants;

/**
 * 数据模块常量
 *
 * @author JiaoJinxin
 */
public class DataConstants {
    private DataConstants() {
    }

    /**
     * 字典-是否-是
     */
    public static final int DICT_YES_NO_YES = 1;

    /**
     * 字典-是否-否
     */
    public static final int DICT_YES_NO_NO = 0;

    /**
     * 列名-租户
     */
    public static final String COLUMN_NAME_TENANT = "tenant_code";

    /**
     * 列名-版本号
     */
    public static final String COLUMN_NAME_VERSION = "version";

    /**
     * 列名-逻辑删除
     */
    public static final String COLUMN_NAME_LOGIC_DELETE = "logic_delete";

    /**
     * 列名-创建时间
     */
    public static final String COLUMN_NAME_CREATE_AT = "create_at";

    /**
     * 列名-创建人
     */
    public static final String COLUMN_NAME_CREATE_BY = "create_by";

    /**
     * 列名-更新时间
     */
    public static final String COLUMN_NAME_UPDATE_AT = "update_at";

    /**
     * 列名-更新人
     */
    public static final String COLUMN_NAME_UPDATE_BY = "update_by";
}
