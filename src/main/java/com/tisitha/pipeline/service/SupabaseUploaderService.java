package com.tisitha.pipeline.service;

import org.springframework.web.multipart.MultipartFile;

public interface SupabaseUploaderService {

    String upload(MultipartFile file, String id);

}
