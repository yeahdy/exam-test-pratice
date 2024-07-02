package com.example.firstservice.service;

import java.io.File;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;

@RequiredArgsConstructor
@Service
public class S3Service {
  private final S3Client s3Client;

  public void putFile(String bucket, String key, File file) {
    s3Client.putObject(
        builder -> {
          builder.bucket(bucket);
          builder.key(key);
        },
        RequestBody.fromFile(file));
  }

  public File getFile(String bucket, String key) {
    File file = new File("build/output/getFile.txt");
    var responseInputStream =
        s3Client.getObject(
            builder -> {
              builder.bucket(bucket);
              builder.key(key);
            });

    try {
      FileUtils.writeByteArrayToFile(file, responseInputStream.readAllBytes());
    } catch (Exception e) {
      // ignore
    }
    return file;
  }
}
