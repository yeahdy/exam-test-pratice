package com.example.userservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class ResponseUser {

    private String email;

    private String name;

    private String userId;

    private List<ResponseOrder> orderList;

}
