package top.jiaojinxin.system.oss.model.query;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import top.jiaojinxin.common.model.query.Query;

import java.io.Serial;

import static top.jiaojinxin.system.oss.constants.ValidatedI18nCodeConstants.GENERATE_DOWNLOAD_EXPIRE_REQUIRED;
import static top.jiaojinxin.system.oss.constants.ValidatedI18nCodeConstants.GENERATE_DOWNLOAD_KEY_REQUIRED;

/**
 * 生成文件获取信息 数据传输对象
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
public class GenerateDownloadQuery implements Query {
    @Serial
    private static final long serialVersionUID = 1289280567867345411L;

    /**
     * 唯一键
     */
    @NotBlank(message = GENERATE_DOWNLOAD_KEY_REQUIRED)
    private String key;

    /**
     * 过期时间，单位：秒
     */
    @NotNull(message = GENERATE_DOWNLOAD_EXPIRE_REQUIRED)
    private Long expire = 60L;
}
