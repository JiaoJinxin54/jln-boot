package top.jiaojinxin.jln.util;

import top.jiaojinxin.jln.oss.OssTemplate;
import top.jiaojinxin.jln.properties.JlnOssProperties;

/**
 * oss对象存储管理类
 *
 * @author JiaoJinxin
 */
public class OssManager {
    private OssManager() {
    }

    /**
     * oss对象存储配置
     */
    private static JlnOssProperties ossProperties;

    /**
     * 获取{@link JlnOssProperties}
     *
     * @return top.jiaojinxin.jln.properties.JlnOssProperties
     * @author JiaoJinxin
     */
    public static JlnOssProperties getOssProperties() {
        return ossProperties;
    }

    /**
     * 设置{@link JlnOssProperties}
     *
     * @param ossProperties {@link JlnOssProperties}
     * @author JiaoJinxin
     */
    public static void setOssProperties(JlnOssProperties ossProperties) {
        OssManager.ossProperties = ossProperties;
    }

    /**
     * oss对象存储模板
     */
    private static OssTemplate ossTemplate;

    /**
     * 获取{@link OssTemplate}
     *
     * @return top.jiaojinxin.jln.oss.OssTemplate
     * @author JiaoJinxin
     */
    public static OssTemplate getOssTemplate() {
        return ossTemplate;
    }

    /**
     * 设置{@link OssTemplate}
     *
     * @param ossTemplate {@link OssTemplate}
     * @author JiaoJinxin
     */
    public static void setOssTemplate(OssTemplate ossTemplate) {
        OssManager.ossTemplate = ossTemplate;
    }
}
