package com.tisitha.pipeline.service;

import com.tisitha.pipeline.exception.CorruptFileException;
import com.tisitha.pipeline.exception.SupabaseUploadingErrorException;
import com.tisitha.pipeline.util.TikaUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class SupabaseUploaderServiceImp implements SupabaseUploaderService{

    private final String supabaseUrl;

    private final String bucket;

    private final RestClient restClient;

    public SupabaseUploaderServiceImp(RestClient.Builder restClientBuilder,
                                      @Value("${supabase.url}") String supabaseUrl,
                                      @Value("${SUPABASE_BUCKET}") String bucket,
                                      @Value("${SUPABASE_API_KEY}") String apiKey) {

        this.supabaseUrl = supabaseUrl;
        this.bucket = bucket;
        this.restClient = restClientBuilder
                .baseUrl(supabaseUrl)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    public String upload(MultipartFile file,String id) {

        String fileName = id + TikaUtils.getFileExtension(file);
        ResponseEntity<String> response = restClient.post()
                    .uri( "/storage/v1/object/" + bucket + "/" + fileName)
                    .contentType(MediaType.parseMediaType(Objects.requireNonNull(file.getContentType())))
                    .body(fileToByte(file))
                    .retrieve()
                    .toEntity(String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return getPublicUrl(fileName);
        } else {
            throw new SupabaseUploadingErrorException("Supebase service failed."+ response);
        }
    }

    private String getPublicUrl(String fileName) {
        return supabaseUrl + "/storage/v1/object/public/" + bucket + "/" + fileName;
    }

    private byte[] fileToByte(MultipartFile file){
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new CorruptFileException(e.getMessage());
        }
    }

}
