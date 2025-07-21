package com.tisitha.pipeline.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tisitha.pipeline.dto.InputDto;
import com.tisitha.pipeline.dto.ResumeData;
import com.tisitha.pipeline.exception.InvalidJsonFormatException;

public class ObjectMapperUtils {

    private static final  ObjectMapper objectMapper = new ObjectMapper();

    public static ResumeData stringToResumeData(String dataString) {
        try {
            return objectMapper.readValue(dataString,ResumeData.class);
        } catch (JsonProcessingException e) {
            throw new InvalidJsonFormatException("ResumeData is invalid json format");
        }
    }

    public static InputDto stringToInputDto(String dataString){
        try {
            return objectMapper.readValue(dataString,InputDto.class);
        } catch (JsonProcessingException e) {
            throw new InvalidJsonFormatException("InputDto is invalid json format");
        }
    }
}
