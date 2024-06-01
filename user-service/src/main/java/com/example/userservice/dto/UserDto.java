package com.example.userservice.dto;

import com.example.userservice.vo.ResponseOrder;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserDto {
    private String email;
    private String name;
    private String password;
    private String userId;
    private Date createdAt;
    private String encryptedPw;

    @Setter
    private List<ResponseOrder> orderList;

    public void setKeyInfo(String userId, String encryptedPw){
        this.userId = userId;
        this.encryptedPw = encryptedPw;
    }

}
