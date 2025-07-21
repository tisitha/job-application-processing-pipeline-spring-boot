package com.tisitha.pipeline.exception;

public class InvalidJsonFormatException extends RuntimeException{

    public InvalidJsonFormatException(String message){
        super(message);
    }
}
