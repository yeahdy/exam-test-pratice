package com.example.catalogservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotEnoughStockException extends RuntimeException {
    public NotEnoughStockException(String message) {
        super(message);
    }
}
