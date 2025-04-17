package com.xyz.EmployeeManagement.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity handleCustomException(CustomException customException){
        ErrorResponseModel errorResponseModel = new ErrorResponseModel(customException.getHttpStatus(), customException.getErrorMessage());
        return ResponseEntity
                .status(errorResponseModel.getHttpStatus())
                .body(errorResponseModel);
    }
}
