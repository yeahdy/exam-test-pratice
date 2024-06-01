package com.example.userservice.config;

import static org.assertj.core.api.Assertions.assertThat;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(
        initializers = ConfigDataApplicationContextInitializer.class,
        classes = JasyptConfig.class)
@EnableEncryptableProperties
public class JasyptConfigTest {

    @Autowired
    @Qualifier("jasyptEncryptor")
    private StringEncryptor stringEncryptor;

    @Test
    void getStringEncryptorTest(){
        //given
        String url = "";
        String username = "";
        String password = "";

        //when
        String encodedUrl = stringEncryptor.encrypt(url);
        String encodedUsername = stringEncryptor.encrypt(username);
        String encodedPassword = stringEncryptor.encrypt(password);

        //then
        assertThat(stringEncryptor.decrypt(encodedUrl)).isEqualTo(url);
        assertThat(stringEncryptor.decrypt(encodedUsername)).isEqualTo(username);
        assertThat(stringEncryptor.decrypt(encodedPassword)).isEqualTo(password);

        System.out.println("encodedUrl :: "+encodedUrl);
        System.out.println("encodedUsername :: "+encodedUsername);
        System.out.println("encodedPassword :: "+encodedPassword);
    }

}