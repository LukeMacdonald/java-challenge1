package com.s3888490.accountservice.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public ApiException(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public ApiException(String string) {
        this.message = string;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.timestamp = ZonedDateTime.now();
    }

    public String getMessage() {
        return this.message;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

}
