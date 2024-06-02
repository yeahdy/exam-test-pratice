package com.example.catalogservice.common.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseMessage<T> {

    private HttpStatus status;
    private String message;
    private T data;

    public static <T> ResponseMessage<T> createSuccess(T data) {
        return new ResponseMessage<>(HttpStatus.CREATED, null, data);
    }

    public static <T> ResponseMessage<T> success(T data, String message) {
        return new ResponseMessage<>(HttpStatus.OK, message, data);
    }

    public static <T> ResponseMessage<T> error(String message, T data) {
        return new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR, message, data);
    }

    public static <T> ResponseMessage<T> setMessage(HttpStatus status, String message, T data) {
        return new ResponseMessage<>(status, message, data);
    }

    public ResponseMessage(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
