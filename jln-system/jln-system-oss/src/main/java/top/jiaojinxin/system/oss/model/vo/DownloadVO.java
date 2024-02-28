package top.jiaojinxin.system.oss.model.vo;

import lombok.Getter;
import lombok.Setter;
import top.jiaojinxin.common.model.DTO;

import java.io.Serial;

/**
 * 下载信息 响应数据对象
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
public class DownloadVO implements DTO {
    @Serial
    private static final long serialVersionUID = 1289280567867345411L;

    /**
     * 文件下载地址
     */
    private String url;

    /**
     * 文件下载请求方式
     */
    private String httpMethod;
}
