package com.tisitha.pipeline.service;

import com.tisitha.pipeline.dto.ResumeData;
import com.tisitha.pipeline.dto.InputDto;
import com.tisitha.pipeline.model.ApplicantData;
import com.tisitha.pipeline.repository.AppRepository;
import com.tisitha.pipeline.util.ObjectMapperUtils;
import com.tisitha.pipeline.util.TikaUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppServiceImp implements AppService{

    private final AppRepository appRepository;
    private final SupabaseUploaderServiceImp uploaderService;
    private final GeminiChatService geminiChatService;
    private final EmailService emailService;

    public void addData(MultipartFile file, InputDto inputDto) {

        //create a UUID for application
        String id = UUID.randomUUID().toString();

        //parse CV file to String
        String stringData = TikaUtils.parseApplication(file);

        //use ai to extract data
        String extractedDataString = geminiChatService.getAiResponse(stringData);

        //convert string to ResumeData object
        ResumeData resumeData = ObjectMapperUtils.stringToResumeData(extractedDataString);

        //save file in cloud storage-supabase
        String url = uploaderService.upload(file,id);

        //create new ApplicantData object for database
        ApplicantData applicantData = new ApplicantData();
        applicantData.setId(id);
        applicantData.setName(inputDto.getName());
        applicantData.setEmail(inputDto.getEmail());
        applicantData.setContactNo(inputDto.getContactNo());
        applicantData.setJobTitle(inputDto.getJobTitle());
        applicantData.setProcessed_timestamp(new Date().toString());
        applicantData.setResumeData(resumeData);
        applicantData.setLinkOfCvFile(url);

        //save to DB
        appRepository.save(applicantData);

        //Send thank-you email to applicant
        emailService.sendSimpleMessage(applicantData.getEmail());

    }

}
