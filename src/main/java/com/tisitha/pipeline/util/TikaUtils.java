package com.tisitha.pipeline.util;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public class TikaUtils {

    private static final Tika tika = new Tika();

    public static String parseApplication(MultipartFile file){
        try {
            return tika.parseToString(file.getInputStream());
        } catch (IOException | TikaException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean fileTypeValidation(MultipartFile file){
        try {
            List<String> validFileTypes = new ArrayList<>(Arrays.asList(
                    "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                    "application/pdf",
                    "application/rtf",
                    "text/plain",
                    "text/html",
                    "application/xhtml+xml",
                    "application/vnd.oasis.opendocument.text"));
            return validFileTypes.contains(tika.detect(file.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getFileExtension(MultipartFile multipartFile){

        try {
            Map<String, String> extensionMap = new HashMap<>();

            extensionMap.put("application/msword", ".doc");
            extensionMap.put("application/vnd.openxmlformats-officedocument.wordprocessingml.document", ".docx");
            extensionMap.put("application/pdf", ".pdf");
            extensionMap.put("application/rtf", ".rtf");
            extensionMap.put("text/plain", ".txt");
            extensionMap.put("text/html", ".html");
            extensionMap.put("application/xhtml+xml", ".html");
            extensionMap.put("application/vnd.oasis.opendocument.text", ".odt");

            String mimeType = tika.detect(multipartFile.getInputStream());
            return extensionMap.getOrDefault(mimeType, "");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
