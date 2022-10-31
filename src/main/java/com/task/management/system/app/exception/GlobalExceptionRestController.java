package com.task.management.system.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionRestController {

    @ExceptionHandler(UserNotValidException.class)
    public ResponseEntity<ApiError> worklogNotAssignException(UserNotValidException exception, WebRequest request) {

        ApiError apiError=new ApiError(new Date(),exception.getMessage(),request.getDescription(false));
        return new ResponseEntity<ApiError>(apiError, HttpStatus.UNAUTHORIZED);
    }


}
