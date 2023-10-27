package com.example.be_api.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ImageUploadException extends RuntimeException{

    public ImageUploadException(String message) {
        super(message);
    }
}
