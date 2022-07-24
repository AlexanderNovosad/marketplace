package com.challenge.marketplace.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionResponse {
    private String message;
    private LocalDateTime dateTime;
    private HttpStatus httpStatus;
    private String details;

    ExceptionResponse(){}

    ExceptionResponse(HttpStatus httpStatus, String message, String details){
        this.httpStatus = httpStatus;
        this.dateTime = LocalDateTime.now();
        this.message = message;
        this.details = details;
    }
}
