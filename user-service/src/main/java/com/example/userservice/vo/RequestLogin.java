package com.example.userservice.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RequestLogin {
    @NotEmpty(message = "Email cannot be null")
    @Size(min = 2, message = "Email not be less than 2 characters")
    private String email;

    @NotEmpty(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be equals or grater than 8 characters")
    private String password;
}
