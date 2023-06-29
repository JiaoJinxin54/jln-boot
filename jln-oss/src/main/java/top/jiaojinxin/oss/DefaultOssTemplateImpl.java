package top.jiaojinxin.oss;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import top.jiaojinxin.oss.properties.OssProperties;

import java.io.InputStream;
import java.net.URL;

/**
 * 默认的oss对象存储模板实现
 *
 * @author JiaoJinxin
 */
public class DefaultOssTemplateImpl implements OssTemplate {

    private final OssProperties ossProperties;

    private AmazonS3 amazonS3;

    public DefaultOssTemplateImpl(OssProperties ossProperties) {
        this.ossProperties = ossProperties;
    }

    @Override
    public void afterSingletonsInstantiated() {
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
    public boolean exists(String bucketName, String key) {
        return this.amazonS3.doesObjectExist(bucketName, key);
    }

    @Override
    public void putObject(PutObjectRequest request) {
        this.amazonS3.putObject(request);
    }

    @Override
    public void delete(String bucketName, String key) {
        if (exists(bucketName, key)) {
            this.amazonS3.deleteObject(bucketName, key);
        }
    }

    @Override
    public InputStream get(String bucketName, String key) {
        if (exists(bucketName, key)) {
            return this.amazonS3.getObject(bucketName, key).getObjectContent();
        }
        return InputStream.nullInputStream();
    }

    @Override
    public URL generatePresignedUrl(GeneratePresignedUrlRequest request) {
        return this.amazonS3.generatePresignedUrl(request);
    }

    @Override
    public String getDefaultBucketName() {
        return this.ossProperties.getBucketName();
    }
}
