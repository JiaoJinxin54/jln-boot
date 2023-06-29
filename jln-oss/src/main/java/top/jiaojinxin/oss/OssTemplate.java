package top.jiaojinxin.oss;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.SmartInitializingSingleton;

import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * oss对象存储模板
 *
 * @author JiaoJinxin
 */
public interface OssTemplate extends SmartInitializingSingleton {

    /**
     * 是否存在
     *
     * @param key 文件路径及名称
     * @return boolean
     * @author JiaoJinxin
     */
    default boolean exists(String key) {
        return exists(getDefaultBucketName(), key);
    }

    /**
     * 是否存在
     *
     * @param bucketName 桶名称
     * @param key        文件路径及名称
     * @return boolean
     * @author JiaoJinxin
     */
    boolean exists(String bucketName, String key);

    /**
     * 是否不存在
     *
     * @param key 文件路径及名称
     * @return boolean
     * @author JiaoJinxin
     */
    default boolean notExists(String key) {
        return !exists(key);
    }

    /**
     * 是否不存在
     *
     * @param bucketName 桶名称
     * @param key        文件路径及名称
     * @return boolean
     * @author JiaoJinxin
     */
    default boolean notExists(String bucketName, String key) {
        return !exists(bucketName, key);
    }

    /**
     * 上传文件
     *
     * @param key         文件路径及名称
     * @param inputStream 文件流
     * @author JiaoJinxin
     */
    default void putObject(String key, InputStream inputStream) {
        putObject(getDefaultBucketName(), key, inputStream);
    }

    /**
     * 上传文件
     *
     * @param key         文件路径及名称
     * @param inputStream 文件流
     * @param acl         权限
     * @author JiaoJinxin
     */
    default void putObject(String key, InputStream inputStream, CannedAccessControlList acl) {
        putObject(getDefaultBucketName(), key, inputStream, acl);
    }

    /**
     * 上传文件
     *
     * @param bucketName  桶名称
     * @param key         文件路径及名称
     * @param inputStream 文件流
     * @author JiaoJinxin
     */
    default void putObject(String bucketName, String key, InputStream inputStream) {
        putObject(bucketName, key, inputStream, null);
    }

    /**
     * 上传文件
     *
     * @param bucketName  桶名称
     * @param key         文件路径及名称
     * @param inputStream 文件流
     * @param acl         权限
     * @author JiaoJinxin
     */
    default void putObject(String bucketName, String key, InputStream inputStream, CannedAccessControlList acl) {
        PutObjectRequest request = new PutObjectRequest(bucketName, key, inputStream, new ObjectMetadata());
        if (acl != null) {
            request.setCannedAcl(acl);
        }
        putObject(request);
    }

    /**
     * 上传文件
     *
     * @param request 上传文件请求
     * @author JiaoJinxin
     */
    void putObject(PutObjectRequest request);

    /**
     * 删除文件
     *
     * @param key 文件路径及名称
     * @author JiaoJinxin
     */
    default void delete(String key) {
        delete(getDefaultBucketName(), key);
    }

    /**
     * 删除文件
     *
     * @param bucketName 桶名称
     * @param key        文件路径及名称
     * @author JiaoJinxin
     */
    void delete(String bucketName, String key);

    /**
     * 获取文件
     *
     * @param key 文件路径及名称
     * @return java.io.InputStream
     * @author JiaoJinxin
     */
    default InputStream get(String key) {
        return get(getDefaultBucketName(), key);
    }

    /**
     * 获取文件
     *
     * @param bucketName 桶名称
     * @param key        文件路径及名称
     * @return java.io.InputStream
     * @author JiaoJinxin
     */
    InputStream get(String bucketName, String key);

    /**
     * 获取文件外链
     *
     * @param key      文件路径及名称
     * @param duration 过期时间
     * @return java.net.URL
     * @author JiaoJinxin
     */
    default URL generatePresignedUrl(String key, Duration duration) {
        return generatePresignedUrl(getDefaultBucketName(), key, duration);
    }

    /**
     * 获取文件外链
     *
     * @param key      文件路径及名称
     * @param expire   过期时间
     * @param timeUnit 时间单位
     * @return java.net.URL
     * @author JiaoJinxin
     */
    default URL generatePresignedUrl(String key, Long expire, TimeUnit timeUnit) {
        return generatePresignedUrl(getDefaultBucketName(), key, expire, timeUnit);
    }

    /**
     * 获取文件外链
     *
     * @param key        文件路径及名称
     * @param expiration 过期时间
     * @return java.net.URL
     * @author JiaoJinxin
     */
    default URL generatePresignedUrl(String key, Date expiration) {
        return generatePresignedUrl(getDefaultBucketName(), key, expiration);
    }

    /**
     * 获取文件外链
     *
     * @param bucketName 桶名称
     * @param key        文件路径及名称
     * @param duration   过期时间
     * @return java.net.URL
     * @author JiaoJinxin
     */
    default URL generatePresignedUrl(String bucketName, String key, Duration duration) {
        return generatePresignedUrl(bucketName, key, new Date(System.currentTimeMillis() + duration.toMillis()));
    }

    /**
     * 获取文件外链
     *
     * @param bucketName 桶名称
     * @param key        文件路径及名称
     * @param expire     过期时间
     * @param timeUnit   时间单位
     * @return java.net.URL
     * @author JiaoJinxin
     */
    default URL generatePresignedUrl(String bucketName, String key, Long expire, TimeUnit timeUnit) {
        return generatePresignedUrl(bucketName, key, new Date(System.currentTimeMillis() + timeUnit.toMillis(expire)));
    }

    /**
     * 获取文件外链
     *
     * @param bucketName 桶名称
     * @param key        文件路径及名称
     * @param expiration 过期时间
     * @return java.net.URL
     * @author JiaoJinxin
     */
    default URL generatePresignedUrl(String bucketName, String key, Date expiration) {
        return generatePresignedUrl(new GeneratePresignedUrlRequest(bucketName, key).withExpiration(expiration));
    }

    /**
     * 生成预签名URL
     *
     * @param request 生成预签名URL请求
     * @return java.net.URL
     * @author JiaoJinxin
     */
    URL generatePresignedUrl(GeneratePresignedUrlRequest request);

    /**
     * 获取默认的桶名称
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    String getDefaultBucketName();
}
