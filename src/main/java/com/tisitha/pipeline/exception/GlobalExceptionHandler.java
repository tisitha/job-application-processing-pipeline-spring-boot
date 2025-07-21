package com.tisitha.pipeline.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidJsonFormatException.class)
    public ProblemDetail InvalidJsonFormatExceptionHandler(InvalidJsonFormatException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InvalidFileTypeException.class)
    public ProblemDetail InvalidFileTypeFormatExceptionHandler(InvalidFileTypeException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(CorruptFileException.class)
    public ProblemDetail CorruptFileExceptionHandler(CorruptFileException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(SupabaseUploadingErrorException.class)
    public ProblemDetail ExternalServiceExceptionHandler(SupabaseUploadingErrorException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_GATEWAY, ex.getMessage());
    }

}
