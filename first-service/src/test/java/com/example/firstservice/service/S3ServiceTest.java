package com.example.firstservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.firstservice.IntegrationTest;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

public class S3ServiceTest extends IntegrationTest {

    @Autowired
    private S3Service s3Service;

    @Test
    public void s3PutAndGetTest() throws Exception {
        //given
        String bucket = "instruction-bucket";
        String key = "instruction.txt";
        File originalFile = new ClassPathResource("static/localStackInstruction.txt").getFile();

        //when
        s3Service.putFile(bucket,key,originalFile);

        //then
        File resultFile =  s3Service.getFile(bucket,key);
        List<String> originalFileLines = FileUtils.readLines(originalFile, StandardCharsets.UTF_8);
        List<String> resultFileLines = FileUtils.readLines(resultFile, StandardCharsets.UTF_8);

        assertThat(resultFileLines).isEqualTo(originalFileLines);
    }

}
