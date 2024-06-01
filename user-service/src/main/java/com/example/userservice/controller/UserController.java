package com.example.userservice.controller;

import com.example.userservice.common.response.ResponseMessage;
import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.Greeting;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final Greeting greeting;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("health_check")
    public String status(){
        return "Working in User Service!";
    }

    @GetMapping("welcome")
    public String welcome(){
        return greeting.getMessage();
    }

    @PostMapping("user")
    public ResponseMessage<ResponseUser> createUser(@RequestBody RequestUser user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
        UserDto createdUser  = userService.createUser(userDto);
        ResponseUser responseUser = modelMapper.map(createdUser,ResponseUser.class);
        return ResponseMessage.createSuccess(responseUser);
    }

}
