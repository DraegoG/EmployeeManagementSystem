package com.xyz.EmployeeManagement.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomException extends RuntimeException {
    private HttpStatus httpStatus;
    private String errorMessage;

    public CustomException(HttpStatus httpStatus, String errorMessage){
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

}
