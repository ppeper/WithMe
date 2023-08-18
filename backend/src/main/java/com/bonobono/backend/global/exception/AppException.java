package com.bonobono.backend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AppException extends RuntimeException{

    private ErrorCode errorCode;

    private String message;
}