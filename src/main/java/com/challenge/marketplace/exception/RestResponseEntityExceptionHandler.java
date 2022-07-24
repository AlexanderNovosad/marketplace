package com.challenge.marketplace.exception;

import com.challenge.marketplace.controller.ProductController;
import com.challenge.marketplace.controller.SaleController;
import com.challenge.marketplace.controller.UserController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice(assignableTypes = {ProductController.class, UserController.class, SaleController.class})
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse error = new ExceptionResponse(HttpStatus.BAD_REQUEST, "Validation Error", ex.getBindingResult().toString());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST, "Wrong type of parameters", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleExceptions(EntityNotFoundException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND, "Not found", exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArithmeticOperationException.class)
    public ResponseEntity<Object> handleExceptions(IllegalArithmeticOperationException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_GATEWAY, "Illegal operation", exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
    }

}
