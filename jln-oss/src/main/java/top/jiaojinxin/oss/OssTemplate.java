package top.jiaojinxin.oss;

import org.springframework.http.HttpMethod;

import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * oss对象存储模板
 *
 * @author JiaoJinxin
 */
public interface OssTemplate extends ReadTemplate, WriteTemplate {

    /**
     * 构建预签名地址
     *
     * @param key      文件路径及名称
     * @param duration 过期时间
     * @return java.net.URL
     */
    default URL generatePresignedUrl(String key, Duration duration) {
        return generatePresignedUrl(getDefaultBucketName(), key, duration);
    }

    /**
     * 构建预签名地址
     *
     * @param key      文件路径及名称
     * @param duration 过期时间
     * @param method   请求方式
     * @return java.net.URL
     */
    default URL generatePresignedUrl(String key, Duration duration, HttpMethod method) {
        return generatePresignedUrl(getDefaultBucketName(), key, duration, method);
    }

    /**
     * 构建预签名地址
     *
     * @param key      文件路径及名称
     * @param expire   过期时间
     * @param timeUnit 时间单位
     * @return java.net.URL
     */
    default URL generatePresignedUrl(String key, Long expire, TimeUnit timeUnit) {
        return generatePresignedUrl(getDefaultBucketName(), key, expire, timeUnit);
    }

    /**
     * 构建预签名地址
     *
     * @param key      文件路径及名称
     * @param expire   过期时间
     * @param timeUnit 时间单位
     * @param method   请求方式
     * @return java.net.URL
     */
    default URL generatePresignedUrl(String key, Long expire, TimeUnit timeUnit, HttpMethod method) {
        return generatePresignedUrl(getDefaultBucketName(), key, expire, timeUnit, method);
    }

    /**
     * 构建预签名地址
     *
     * @param key        文件路径及名称
     * @param expiration 过期时间
     * @return java.net.URL
     */
    default URL generatePresignedUrl(String key, Date expiration) {
        return generatePresignedUrl(getDefaultBucketName(), key, expiration);
    }

    /**
     * 构建预签名地址
     *
     * @param key        文件路径及名称
     * @param expiration 过期时间
     * @param method     请求方式
     * @return java.net.URL
     */
    default URL generatePresignedUrl(String key, Date expiration, HttpMethod method) {
        return generatePresignedUrl(getDefaultBucketName(), key, expiration, method);
    }

    /**
     * 构建预签名地址
     *
     * @param bucketName 桶名称
     * @param key        文件路径及名称
     * @param duration   过期时间
     * @return java.net.URL
     */
    default URL generatePresignedUrl(String bucketName, String key, Duration duration) {
        return generatePresignedUrl(bucketName, key, duration, HttpMethod.GET);
    }

    /**
     * 构建预签名地址
     *
     * @param bucketName 桶名称
     * @param key        文件路径及名称
     * @param duration   过期时间
     * @param method     请求方式
     * @return java.net.URL
     */
    default URL generatePresignedUrl(String bucketName, String key, Duration duration, HttpMethod method) {
        return generatePresignedUrl(bucketName, key, new Date(System.currentTimeMillis() + duration.toMillis()), method);
    }

    /**
     * 构建预签名地址
     *
     * @param bucketName 桶名称
     * @param key        文件路径及名称
     * @param expire     过期时间
     * @param timeUnit   时间单位
     * @return java.net.URL
     */
    default URL generatePresignedUrl(String bucketName, String key, Long expire, TimeUnit timeUnit) {
        return generatePresignedUrl(bucketName, key, expire, timeUnit, HttpMethod.GET);
    }

    /**
     * 构建预签名地址
     *
     * @param bucketName 桶名称
     * @param key        文件路径及名称
     * @param expire     过期时间
     * @param timeUnit   时间单位
     * @param method     请求方式
     * @return java.net.URL
     */
    default URL generatePresignedUrl(String bucketName, String key, Long expire, TimeUnit timeUnit, HttpMethod method) {
        return generatePresignedUrl(bucketName, key, new Date(System.currentTimeMillis() + timeUnit.toMillis(expire)), method);
    }

    /**
     * 构建预签名地址
     *
     * @param bucketName 桶名称
     * @param key        文件路径及名称
     * @param expiration 过期时间
     * @return java.net.URL
     */
    default URL generatePresignedUrl(String bucketName, String key, Date expiration) {
        return generatePresignedUrl(bucketName, key, expiration, HttpMethod.GET);
    }

    /**
     * 构建预签名地址
     *
     * @param bucketName 桶名称
     * @param key        文件路径及名称
     * @param expiration 过期时间
     * @param method     请求方式
     * @return java.net.URL
     */
    URL generatePresignedUrl(String bucketName, String key, Date expiration, HttpMethod method);
}
