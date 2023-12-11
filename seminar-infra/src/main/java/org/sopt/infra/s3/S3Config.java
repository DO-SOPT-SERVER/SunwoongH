package org.sopt.infra.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {
    private static final String AWS_ACCESS_KEY_ID = "aws.accessKeyId";
    private static final String AWS_SECRET_ACCESS_KEY = "aws.secretAccessKey";
    @Value("${aws-property.access-key}")
    private String accessKey;
    @Value("${aws-property.secret-key}")
    private String secretKey;
    @Value("${aws-property.aws-region}")
    private String region;

    @Bean
    public S3Client s3Client() {
        System.setProperty(AWS_ACCESS_KEY_ID, accessKey);
        System.setProperty(AWS_SECRET_ACCESS_KEY, secretKey);
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(SystemPropertyCredentialsProvider.create())
                .build();
    }
}
