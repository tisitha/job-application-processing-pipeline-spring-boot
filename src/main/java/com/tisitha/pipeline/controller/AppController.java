package com.tisitha.pipeline.controller;

import com.tisitha.pipeline.dto.InputDto;
import com.tisitha.pipeline.service.AppService;
import com.tisitha.pipeline.util.ObjectMapperUtils;
import com.tisitha.pipeline.util.TikaUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/application")
public class AppController {

    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @PostMapping
    public ResponseEntity<String> addData(@RequestPart MultipartFile file,@RequestPart String data){
        if(!TikaUtils.fileTypeValidation(file)){
            return new ResponseEntity<>("File type not valid.",HttpStatus.BAD_REQUEST);
        }
        InputDto inputDto = ObjectMapperUtils.stringToInputDto(data);
        appService.addData(file,inputDto);
        return new ResponseEntity<>("Successfully submitted your application.", HttpStatus.OK);
    }

}
