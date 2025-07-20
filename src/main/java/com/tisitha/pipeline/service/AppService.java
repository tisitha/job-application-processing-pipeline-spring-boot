package com.tisitha.pipeline.service;

import com.tisitha.pipeline.dto.InputDto;
import org.springframework.web.multipart.MultipartFile;

public interface AppService {

    void addData(MultipartFile file, InputDto data);

}