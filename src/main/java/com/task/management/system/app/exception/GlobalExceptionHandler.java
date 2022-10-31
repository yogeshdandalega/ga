package com.task.management.system.app.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public String worklogNotAssignException(Model m) {
        m.addAttribute("massg", "Null Pointer Exception Occured");
        return "null_page";
    }
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public String internalServerError(Model m) {
        m.addAttribute("massg", "Internal Server Error");
        return "null_page";
    }
    @ExceptionHandler(Exception.class)
    public String generalException(Model m) {
        m.addAttribute("massg", "Exception has occured");
        return "null_page";
    }
}
