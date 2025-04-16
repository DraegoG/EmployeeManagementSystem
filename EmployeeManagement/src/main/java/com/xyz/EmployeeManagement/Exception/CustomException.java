package com.xyz.EmployeeManagement.Exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomException extends RuntimeException {
    HttpStatus httpStatus;
    String errorMessage;

    public CustomException(HttpStatus httpStatus, String errorMessage){
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

}
