package com.example.examservice.config;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProviderChain;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

// 테스트를 위한 가짜 AWS 인증정보
@Configuration
public class S3Config {
  @Value("${aws.endpoint}")
  String awsEndpoint;

  @Bean
  public AwsCredentialsProvider awsCredentialsProvider() {
    // aws sdk에서 aws서비스를 사용하기 위해 필요한 인증 정보
    return AwsCredentialsProviderChain.builder()
        .reuseLastProviderEnabled(true)
        .credentialsProviders(
            List.of(
                DefaultCredentialsProvider.create(),
                StaticCredentialsProvider.create(AwsBasicCredentials.create("ellie", "1234"))))
        .build();
  }

  @Bean
  public S3Client s3Client() {
    // aws s3 서비스 사용을 위한 클라이언트 제공. 내부적으로 API 호출
    return S3Client.builder()
        .credentialsProvider(awsCredentialsProvider())
        .region(Region.AP_NORTHEAST_2)
        .endpointOverride(URI.create(awsEndpoint))
        .build();
  }
}
