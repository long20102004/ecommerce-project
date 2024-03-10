package com.example.ecommerce.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserExistedException.class)
    public String handleUserExistedException(UserExistedException e){
        return "redirect:/sign-up";
    }
    @ExceptionHandler(NoSuchElementException.class)
    public String handle404Exception(){
        return "redirect:/not-found";
    }
}
