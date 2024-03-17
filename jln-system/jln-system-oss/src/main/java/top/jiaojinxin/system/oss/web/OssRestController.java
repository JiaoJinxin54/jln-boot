package top.jiaojinxin.system.oss.web;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jiaojinxin.oss.OssTemplate;
import top.jiaojinxin.system.oss.model.query.GenerateDownloadQuery;
import top.jiaojinxin.system.oss.model.query.GenerateUploadQuery;
import top.jiaojinxin.system.oss.model.vo.DownloadVO;
import top.jiaojinxin.system.oss.model.vo.UploadVO;
import top.jiaojinxin.util.ValidatedUtil;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;

/**
 * 对象存储
 *
 * @author JiaoJinxin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("${jln.oss.web.base-path:/oss}")
public class OssRestController {

    private final OssTemplate ossTemplate;

    /**
     * 生成s3唯一键及文件上传地址
     *
     * @param dto 参数
     * @return java.lang.String
     */
    @PostMapping("${jln.oss.web.generate-upload:/generate/upload}")
    public UploadVO generateUpload(@RequestBody GenerateUploadQuery dto) {
        // 参数验证
        ValidatedUtil.validated(dto);
        // 年月日字符串
        String dateStr = LocalDate.now().format(DatePattern.PURE_DATE_FORMATTER);
        // UUID
        String uid = IdUtil.fastSimpleUUID();
        // s3文件的键
        String key = StrFormatter.format("{}/{}/{}/{}", dto.getBasePath(), dateStr, uid, dto.getFileName());
        // 请求方式
        HttpMethod httpMethod = HttpMethod.PUT;
        // 生成URL
        URL url = ossTemplate.generatePresignedUrl(key, Duration.ofSeconds(dto.getExpire()), httpMethod);
        // 响应
        UploadVO vo = new UploadVO();
        vo.setUrl(url.toString());
        vo.setHttpMethod(httpMethod.name());
        vo.setKey(key);
        return vo;
    }

    @PostMapping("${jln.oss.web.generate-download:/generate/download}")
    public DownloadVO generateDownload(@RequestBody GenerateDownloadQuery dto) {
        // 参数验证
        ValidatedUtil.validated(dto);
        // 生成URL
        URL url = ossTemplate.generatePresignedUrl(dto.getKey(), Duration.ofSeconds(dto.getExpire()));
        // 响应
        DownloadVO vo = new DownloadVO();
        vo.setUrl(url.toString());
        vo.setHttpMethod(HttpMethod.GET.name());
        return vo;
    }
}
