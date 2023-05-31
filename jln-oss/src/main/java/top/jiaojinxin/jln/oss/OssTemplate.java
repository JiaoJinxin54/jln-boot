package top.jiaojinxin.jln.oss;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

/**
 * oss对象存储模板
 *
 * @author JiaoJinxin
 */
public interface OssTemplate extends InitializingBean {

    /**
     * 上传文件，并设置权限
     *
     * @param key  文件名（需保证唯一性）
     * @param file 文件
     * @param acl  访问权限
     * @return java.lang.String
     * @author JiaoJinxin
     */
    default String upload(String key, File file, CannedAccessControlList acl) {
        return upload(null, key, file, acl);
    }

    /**
     * 上传文件，并设置权限
     *
     * @param bucketName 桶名称
     * @param key        文件名（需保证唯一性）
     * @param file       文件
     * @param acl        访问权限
     * @return java.lang.String
     * @author JiaoJinxin
     */
    String upload(String bucketName, String key, File file, CannedAccessControlList acl);

    /**
     * 上传文件
     *
     * @param key  文件名（需保证唯一性）
     * @param file 文件
     * @return java.lang.String
     * @author JiaoJinxin
     */
    default String upload(String key, File file) {
        return upload(null, key, file);
    }

    /**
     * 上传文件
     *
     * @param bucketName 桶名称
     * @param key        文件名（需保证唯一性）
     * @param file       文件
     * @return java.lang.String
     * @author JiaoJinxin
     */
    default String upload(String bucketName, String key, File file) {
        return upload(bucketName, key, file, null);
    }

    /**
     * 删除文件
     *
     * @param key 文件名称
     * @author JiaoJinxin
     */
    default void delete(String key) {
        delete(null, key);
    }

    /**
     * 删除文件
     *
     * @param bucketName 桶名称
     * @param key        文件名称
     * @author JiaoJinxin
     */
    void delete(String bucketName, String key);

    /**
     * 获取文件
     *
     * @param key 文件名称
     * @return java.io.InputStream
     * @author JiaoJinxin
     */
    default InputStream get(String key) {
        return get(null, key);
    }

    /**
     * 获取文件
     *
     * @param bucketName 桶名称
     * @param key        文件名称
     * @return java.io.InputStream
     * @author JiaoJinxin
     */
    InputStream get(String bucketName, String key);

    /**
     * 获取文件外链
     *
     * @param key        文件名称
     * @param expiration 过期时间
     * @return java.lang.String
     * @author JiaoJinxin
     */
    default String getObjectURL(String key, Date expiration) {
        return getObjectURL(null, key, expiration);
    }

    /**
     * 获取文件外链
     *
     * @param bucketName 桶名称
     * @param key        文件名称
     * @param expiration 过期时间
     * @return java.lang.String
     * @author JiaoJinxin
     */
    String getObjectURL(String bucketName, String key, Date expiration);
}
