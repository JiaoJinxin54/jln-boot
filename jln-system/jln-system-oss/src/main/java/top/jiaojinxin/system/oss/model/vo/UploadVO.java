package top.jiaojinxin.system.oss.model.vo;

import lombok.Getter;
import lombok.Setter;
import top.jiaojinxin.common.model.DTO;

import java.io.Serial;

/**
 * 上传信息 响应数据对象
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
public class UploadVO implements DTO {
    @Serial
    private static final long serialVersionUID = -8766878279667364839L;

    /**
     * 文件上传地址
     */
    private String url;

    /**
     * 文件上传请求方式
     */
    private String httpMethod;

    /**
     * 唯一键
     */
    private String key;
}
