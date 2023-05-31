package top.jiaojinxin.jln.oss;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.HttpMethod;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import top.jiaojinxin.jln.properties.JlnOssProperties;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.Optional;

/**
 * 默认的oss对象存储模板实现
 *
 * @author JiaoJinxin
 */
public class DefaultOssTemplateImpl implements OssTemplate {

    private final JlnOssProperties ossProperties;

    private AmazonS3 amazonS3;

    public DefaultOssTemplateImpl(JlnOssProperties ossProperties) {
        this.ossProperties = ossProperties;
    }

    @Override
    public void afterPropertiesSet() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setProtocol(Protocol.HTTP);
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(
                ossProperties.getServiceEndpoint(), ossProperties.getSigningRegion());
        AWSCredentials awsCredentials = new BasicAWSCredentials(ossProperties.getAccessKey(),
                ossProperties.getSecretKey());
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
        this.amazonS3 = AmazonS3Client.builder()
                .withEndpointConfiguration(endpointConfiguration)
                .withClientConfiguration(clientConfiguration)
                .withCredentials(awsCredentialsProvider)
                .disableChunkedEncoding()
                .withPathStyleAccessEnabled(true)
                .build();
    }

    @Override
    public String upload(String bucketName, String key, File file, CannedAccessControlList acl) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(getBucketName(bucketName), key, file);
        if (acl != null) {
            putObjectRequest.setCannedAcl(acl);
        }
        this.amazonS3.putObject(putObjectRequest);
        return key;
    }

    @Override
    public void delete(String bucketName, String key) {
        if (this.amazonS3.doesObjectExist(getBucketName(bucketName), key)) {
            this.amazonS3.deleteObject(getBucketName(bucketName), key);
        }
    }

    @Override
    public InputStream get(String bucketName, String key) {
        bucketName = getBucketName(bucketName);
        if (this.amazonS3.doesObjectExist(bucketName, key)) {
            return this.amazonS3.getObject(bucketName, key).getObjectContent();
        }
        return InputStream.nullInputStream();
    }

    @Override
    public String getObjectURL(String bucketName, String key, Date expiration) {
        return this.amazonS3.generatePresignedUrl(getBucketName(bucketName), key, expiration, HttpMethod.GET).toString();
    }

    /**
     * 获取桶名称，若为空则取默认桶
     *
     * @param bucketName 桶名称
     * @return java.lang.String
     * @author JiaoJinxin
     */
    private String getBucketName(String bucketName) {
        return Optional.ofNullable(bucketName).orElse(ossProperties.getBucketName());
    }
}
