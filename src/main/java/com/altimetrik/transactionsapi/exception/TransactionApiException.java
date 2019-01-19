package com.altimetrik.transactionsapi.exception;

import org.springframework.http.HttpStatus;

public class TransactionApiException extends RuntimeException {

    private int status;
    private String message;
    private HttpStatus httpStatus;


    public TransactionApiException(int status, String message, HttpStatus httpStatus) {
        this.status = status;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
