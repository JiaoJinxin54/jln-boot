package top.jiaojinxin.system.oss.model.query;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import top.jiaojinxin.common.model.query.Query;

import java.io.Serial;

import static top.jiaojinxin.system.oss.constants.ValidatedI18nCodeConstants.GENERATE_KEY_BASE_PATH_REQUIRED;
import static top.jiaojinxin.system.oss.constants.ValidatedI18nCodeConstants.GENERATE_KEY_EXPIRE_REQUIRED;
import static top.jiaojinxin.system.oss.constants.ValidatedI18nCodeConstants.GENERATE_KEY_FILE_NAME_REQUIRED;

/**
 * 生成文件上传信息 数据传输对象
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
public class GenerateUploadQuery implements Query {
    @Serial
    private static final long serialVersionUID = -6963234235552531972L;

    /**
     * 存储基础路径
     */
    @NotBlank(message = GENERATE_KEY_BASE_PATH_REQUIRED)
    private String basePath;

    /**
     * 文件名
     */
    @NotBlank(message = GENERATE_KEY_FILE_NAME_REQUIRED)
    private String fileName;

    /**
     * 过期时间，单位：秒
     */
    @NotNull(message = GENERATE_KEY_EXPIRE_REQUIRED)
    private Long expire = 60L;
}
