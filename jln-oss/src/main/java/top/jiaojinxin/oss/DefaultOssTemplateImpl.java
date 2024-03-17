package top.jiaojinxin.oss;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.http.HttpMethod;
import top.jiaojinxin.properties.OssProperties;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.HEAD;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

/**
 * 默认的oss对象存储模板实现
 *
 * @author JiaoJinxin
 */
public class DefaultOssTemplateImpl implements OssTemplate, SmartInitializingSingleton {

    private final OssProperties ossProperties;

    private AmazonS3 amazonS3;

    /**
     * 默认的oss对象存储模板实现
     *
     * @param ossProperties {@link OssProperties}
     */
    public DefaultOssTemplateImpl(OssProperties ossProperties) {
        this.ossProperties = ossProperties;
    }

    @Override
    public void afterSingletonsInstantiated() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setProtocol(ossProperties.getProtocol());
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
    public void put(String bucketName, String key, InputStream inputStream) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        try {
            objectMetadata.setContentLength(inputStream.available());
        } catch (IOException ignored) {
        }
        this.amazonS3.putObject(new PutObjectRequest(bucketName, key, inputStream, objectMetadata));
    }

    @Override
    public void put(String bucketName, String key, File file) {
        this.amazonS3.putObject(new PutObjectRequest(bucketName, key, file));
    }

    @Override
    public void delete(String bucketName, String key) {
        if (exists(bucketName, key)) {
            this.amazonS3.deleteObject(bucketName, key);
        }
    }

    @Override
    public void deleteBatch(String bucketName, String[] keys) {
        this.amazonS3.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
    }

    @Override
    public InputStream get(String bucketName, String key) {
        if (exists(bucketName, key)) {
            return this.amazonS3.getObject(bucketName, key).getObjectContent();
        }
        return InputStream.nullInputStream();
    }

    @Override
    public URL generatePresignedUrl(String bucketName, String key, Date expiration, HttpMethod method) {
        String name;
        if (method == GET || method == POST || method == PUT || method == DELETE || method == HEAD || method == PATCH) {
            name = method.name();
        } else {
            name = GET.name();
        }
        com.amazonaws.HttpMethod httpMethod = com.amazonaws.HttpMethod.valueOf(name);
        return this.amazonS3.generatePresignedUrl(new GeneratePresignedUrlRequest(bucketName, key, httpMethod).withExpiration(expiration));
    }

    @Override
    public String getDefaultBucketName() {
        return this.ossProperties.getBucketName();
    }
}
