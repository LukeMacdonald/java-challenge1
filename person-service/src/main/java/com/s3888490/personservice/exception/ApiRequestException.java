package com.s3888490.personservice.exception;

public class ApiRequestException extends RuntimeException {

    public ApiRequestException (String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable throwable) {
        super(message, throwable);
    }
}