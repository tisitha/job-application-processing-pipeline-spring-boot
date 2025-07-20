package com.tisitha.pipeline.model;

import com.tisitha.pipeline.dto.ResumeData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class ApplicantData {

    @Id
    private String id;

    private String name;

    private String email;

    private String contactNo;

    private String jobTitle;

    private String processed_timestamp;

    private ResumeData resumeData;

    private String linkOfCvFile;

}
