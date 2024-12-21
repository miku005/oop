package com.oop.exception;

import com.oop.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class HandleException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalException(
            ResourceNotFound e, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                e.getMessage(),
                request.getDescription(false)
             );
        return new ResponseEntity<>("ErrorDetails", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
