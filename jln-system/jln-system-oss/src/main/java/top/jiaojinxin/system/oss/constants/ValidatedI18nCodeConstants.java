package top.jiaojinxin.system.oss.constants;

/**
 * 校验国际化码常量
 *
 * @author JiaoJinxin
 */
public class ValidatedI18nCodeConstants {
    private ValidatedI18nCodeConstants() {
    }

    /**
     * 生成上传信息.基础路径.必填
     */
    public static final String GENERATE_KEY_BASE_PATH_REQUIRED = "validated.generateUpload.basePath.required";

    /**
     * 生成上传信息.文件名.必填
     */
    public static final String GENERATE_KEY_FILE_NAME_REQUIRED = "validated.generateUpload.fileName.required";

    /**
     * 生成上传信息.过期时间.必填
     */
    public static final String GENERATE_KEY_EXPIRE_REQUIRED = "validated.generateUpload.expire.required";

    /**
     * 生成下载信息.文件名.必填
     */
    public static final String GENERATE_DOWNLOAD_KEY_REQUIRED = "validated.generateDownload.key.required";

    /**
     * 生成下载信息.过期时间.必填
     */
    public static final String GENERATE_DOWNLOAD_EXPIRE_REQUIRED = "validated.generateDownload.expire.required";
}
