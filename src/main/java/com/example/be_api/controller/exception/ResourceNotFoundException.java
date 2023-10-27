package com.example.be_api.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Ném ngoại lệ nếu không tồn tại
// Hàm ngoại lệ tùy chỉnh
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){

        super(message);
    }
}
