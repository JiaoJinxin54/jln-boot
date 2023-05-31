package top.jiaojinxin.jln.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import top.jiaojinxin.jln.oss.OssTemplate;
import top.jiaojinxin.jln.properties.JlnOssProperties;
import top.jiaojinxin.jln.util.OssManager;

/**
 * oss对象存储自动注册
 *
 * @author JiaoJinxin
 */
public class JlnOssAutoRegistration {

    @Autowired
    public void setJlnOssProperties(JlnOssProperties ossProperties) {
        OssManager.setOssProperties(ossProperties);
    }

    @Autowired
    public void setOssTemplate(OssTemplate ossTemplate) {
        OssManager.setOssTemplate(ossTemplate);
    }
}
