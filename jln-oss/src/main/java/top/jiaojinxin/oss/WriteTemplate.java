package top.jiaojinxin.oss;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * 写操作模板
 *
 * @author JiaoJinxin
 */
public interface WriteTemplate extends BaseTemplate {

    /**
     * 上传文件
     *
     * @param key         文件路径及名称
     * @param inputStream 文件流
     */
    default void put(String key, InputStream inputStream) {
        put(getDefaultBucketName(), key, inputStream);
    }

    /**
     * 上传文件
     *
     * @param bucketName  桶名称
     * @param key         文件路径及名称
     * @param inputStream 文件流
     */
    void put(String bucketName, String key, InputStream inputStream);

    /**
     * 上传文件
     *
     * @param key  文件路径及名称
     * @param file 文件
     */
    default void put(String key, File file) {
        put(getDefaultBucketName(), key, file);
    }

    /**
     * 上传文件
     *
     * @param bucketName 桶名称
     * @param key        文件路径及名称
     * @param file       文件
     */
    void put(String bucketName, String key, File file);

    /**
     * 删除文件
     *
     * @param key 文件路径及名称
     */
    default void delete(String key) {
        delete(getDefaultBucketName(), key);
    }

    /**
     * 删除文件
     *
     * @param bucketName 桶名称
     * @param key        文件路径及名称
     */
    void delete(String bucketName, String key);

    /**
     * 批量删除文件
     *
     * @param keys 唯一键数组
     */
    default void deleteBatch(List<String> keys) {
        deleteBatch(getDefaultBucketName(), keys);
    }

    /**
     * 批量删除文件
     *
     * @param keys 唯一键数组
     */
    default void deleteBatch(String[] keys) {
        deleteBatch(getDefaultBucketName(), keys);
    }

    /**
     * 批量删除文件
     *
     * @param bucketName 桶名称
     * @param keys       文件路径及名称数组
     */
    default void deleteBatch(String bucketName, List<String> keys) {
        deleteBatch(bucketName, keys.toArray(new String[0]));
    }

    /**
     * 批量删除文件
     *
     * @param bucketName 桶名称
     * @param keys       文件路径及名称数组
     */
    void deleteBatch(String bucketName, String[] keys);
}
