package top.jiaojinxin.jln.properties;

import com.baomidou.mybatisplus.annotation.DbType;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serial;
import java.io.Serializable;

/**
 * Mybatis-Plus需要配置的参数
 *
 * @author JiaoJinxin
 */
@ConfigurationProperties(prefix = "jln.mybatis-plus")
public class JlnMpProperties implements Serializable {

    @Serial
    private static final long serialVersionUID = -1634060037370047408L;

    /**
     * 数据库类型
     */
    private DbType dbType;

    /**
     * 忽略租户字段条件的表名集合，默认：空
     */
    private String[] ignoreTenantTables = {};

    /**
     * 单页分页条数限制，默认：100
     */
    private Long maxLimit = 100L;

    /**
     * 溢出总页数后是否进行处理，默认：true
     */
    private boolean overflow = true;

    /**
     * 是否使用防止全表更新与删除插件，默认：true
     */
    private boolean isBlockAttack = true;

    /**
     * 是否使用数据权限处理插件，默认：false
     */
    private boolean isDataPermission = false;

    /**
     * 是否使用动态表名插件，默认：false
     */
    private boolean isDynamicTableName = false;

    /**
     * 是否使用垃圾SQL拦截插件，默认：false
     */
    private boolean isIllegalSQL = false;

    /**
     * 是否使用占位符替换插件，默认：false
     */
    private boolean isReplacePlaceholder = false;

    /**
     * 占位符替换
     */
    private String escapeSymbol;

    /**
     * 构造方法
     *
     * @author JiaoJinxin
     */
    public JlnMpProperties() {
    }

    /**
     * 获取{@link JlnMpProperties#dbType}
     *
     * @return com.baomidou.mybatisplus.annotation.DbType
     * @author JiaoJinxin
     */
    public DbType getDbType() {
        return dbType;
    }

    /**
     * 设置{@link JlnMpProperties#dbType}
     *
     * @param dbType {@link JlnMpProperties#dbType}
     * @author JiaoJinxin
     */
    public void setDbType(DbType dbType) {
        this.dbType = dbType;
    }

    /**
     * 获取{@link JlnMpProperties#ignoreTenantTables}
     *
     * @return java.lang.String[]
     * @author JiaoJinxin
     */
    public String[] getIgnoreTenantTables() {
        return ignoreTenantTables;
    }

    /**
     * 设置{@link JlnMpProperties#ignoreTenantTables}
     *
     * @param ignoreTenantTables {@link JlnMpProperties#ignoreTenantTables}
     * @author JiaoJinxin
     */
    public void setIgnoreTenantTables(String[] ignoreTenantTables) {
        this.ignoreTenantTables = ignoreTenantTables;
    }

    /**
     * 获取{@link JlnMpProperties#maxLimit}
     *
     * @return java.lang.Long
     * @author JiaoJinxin
     */
    public Long getMaxLimit() {
        return maxLimit;
    }

    /**
     * 设置{@link JlnMpProperties#maxLimit}
     *
     * @param maxLimit {@link JlnMpProperties#maxLimit}
     * @author JiaoJinxin
     */
    public void setMaxLimit(Long maxLimit) {
        this.maxLimit = maxLimit;
    }

    /**
     * 获取{@link JlnMpProperties#overflow}
     *
     * @return boolean
     * @author JiaoJinxin
     */
    public boolean isOverflow() {
        return overflow;
    }

    /**
     * 设置{@link JlnMpProperties#overflow}
     *
     * @param overflow {@link JlnMpProperties#overflow}
     * @author JiaoJinxin
     */
    public void setOverflow(boolean overflow) {
        this.overflow = overflow;
    }

    /**
     * 获取{@link JlnMpProperties#isBlockAttack}
     *
     * @return boolean
     * @author JiaoJinxin
     */
    public boolean isBlockAttack() {
        return isBlockAttack;
    }

    /**
     * 设置{@link JlnMpProperties#isBlockAttack}
     *
     * @param blockAttack {@link JlnMpProperties#isBlockAttack}
     * @author JiaoJinxin
     */
    public void setBlockAttack(boolean blockAttack) {
        isBlockAttack = blockAttack;
    }

    /**
     * 获取{@link JlnMpProperties#isDataPermission}
     *
     * @return boolean
     * @author JiaoJinxin
     */
    public boolean isDataPermission() {
        return isDataPermission;
    }

    /**
     * 设置{@link JlnMpProperties#isDataPermission}
     *
     * @param dataPermission {@link JlnMpProperties#isDataPermission}
     * @author JiaoJinxin
     */
    public void setDataPermission(boolean dataPermission) {
        isDataPermission = dataPermission;
    }

    /**
     * 获取{@link JlnMpProperties#isDynamicTableName}
     *
     * @return boolean
     * @author JiaoJinxin
     */
    public boolean isDynamicTableName() {
        return isDynamicTableName;
    }

    /**
     * 获取{@link JlnMpProperties#isDynamicTableName}
     *
     * @param dynamicTableName {@link JlnMpProperties#isDynamicTableName}
     * @author JiaoJinxin
     */
    public void setDynamicTableName(boolean dynamicTableName) {
        isDynamicTableName = dynamicTableName;
    }

    /**
     * 获取{@link JlnMpProperties#isIllegalSQL}
     *
     * @return boolean
     * @author JiaoJinxin
     */
    public boolean isIllegalSQL() {
        return isIllegalSQL;
    }

    /**
     * 设置{@link JlnMpProperties#isIllegalSQL}
     *
     * @param illegalSQL {@link JlnMpProperties#isIllegalSQL}
     * @author JiaoJinxin
     */
    public void setIllegalSQL(boolean illegalSQL) {
        isIllegalSQL = illegalSQL;
    }

    /**
     * 获取{@link JlnMpProperties#isReplacePlaceholder}
     *
     * @return boolean
     * @author JiaoJinxin
     */
    public boolean isReplacePlaceholder() {
        return isReplacePlaceholder;
    }

    /**
     * 设置{@link JlnMpProperties#isReplacePlaceholder}
     *
     * @param replacePlaceholder {@link JlnMpProperties#isReplacePlaceholder}
     * @author JiaoJinxin
     */
    public void setReplacePlaceholder(boolean replacePlaceholder) {
        isReplacePlaceholder = replacePlaceholder;
    }

    /**
     * 获取{@link JlnMpProperties#escapeSymbol}
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    public String getEscapeSymbol() {
        return escapeSymbol;
    }

    /**
     * 设置{@link JlnMpProperties#escapeSymbol}
     *
     * @param escapeSymbol {@link JlnMpProperties#escapeSymbol}
     * @author JiaoJinxin
     */
    public void setEscapeSymbol(String escapeSymbol) {
        this.escapeSymbol = escapeSymbol;
    }
}
