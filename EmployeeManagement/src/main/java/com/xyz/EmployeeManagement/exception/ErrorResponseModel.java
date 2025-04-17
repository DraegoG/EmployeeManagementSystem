package com.xyz.EmployeeManagement.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponseModel {
    private HttpStatus httpStatus;
    private String errorMessage;

    public ErrorResponseModel(HttpStatus httpStatus, String errorMessage){
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }
}
