package com.example.userservice.dto;

import java.util.Date;
import lombok.Getter;

@Getter
public class UserDto {

    private String email;

    private String name;

    private String password;

    private String userId;

    private Date createdAt;

    private String encryptedPw;

    public void setKeyInfo(String userId, String encryptedPw){
        this.userId = userId;
        this.encryptedPw = encryptedPw;
    }

}
