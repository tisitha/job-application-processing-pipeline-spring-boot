package com.tisitha.pipeline.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tisitha.pipeline.dto.InputDto;
import com.tisitha.pipeline.dto.ResumeData;

public class ObjectMapperUtils {

    private static final  ObjectMapper objectMapper = new ObjectMapper();

    public static ResumeData stringToResumeData(String dataString) {
        try {
            return objectMapper.readValue(dataString,ResumeData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static InputDto stringToInputDto(String dataString){
        try {
            return objectMapper.readValue(dataString,InputDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
