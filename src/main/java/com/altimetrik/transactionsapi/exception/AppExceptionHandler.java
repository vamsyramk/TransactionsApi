package com.altimetrik.transactionsapi.exception;

import com.altimetrik.transactionsapi.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(TransactionApiException.class)
    public ResponseEntity<ApiResponse> handleTransactionExceptions(TransactionApiException ex) {
        ApiResponse apiResponse = new ApiResponse();
//        if (ex.getHttpStatus().value() == 204) {
//            apiResponse.setStatus("Not Updated");
//            apiResponse.setMessage("The transaction is in the past time");
//        }
        return new ResponseEntity<>(apiResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericExceptions(Exception ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus("Not Updated");
        apiResponse.setMessage("The transaction failed to update due to bad request");
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
