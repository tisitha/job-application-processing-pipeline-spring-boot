package com.tisitha.pipeline.exception;

public class SupabaseUploadingErrorException extends RuntimeException{

    public SupabaseUploadingErrorException(String message){
        super(message);
    }
}
