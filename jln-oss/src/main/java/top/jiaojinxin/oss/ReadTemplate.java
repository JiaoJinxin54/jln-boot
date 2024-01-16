package top.jiaojinxin.oss;

import java.io.InputStream;

/**
 * 读操作模板
 *
 * @author JiaoJinxin
 */
public interface ReadTemplate extends BaseTemplate {

    /**
     * 是否不存在
     *
     * @param key 文件路径及名称
     * @return boolean
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
     */
    default boolean notExists(String bucketName, String key) {
        return !exists(bucketName, key);
    }

    /**
     * 是否存在
     *
     * @param key 文件路径及名称
     * @return boolean
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
     */
    boolean exists(String bucketName, String key);

    /**
     * 获取文件
     *
     * @param key 文件路径及名称
     * @return java.io.InputStream
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
     */
    InputStream get(String bucketName, String key);
}
