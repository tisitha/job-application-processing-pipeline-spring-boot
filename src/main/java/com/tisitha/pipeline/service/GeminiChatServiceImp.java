package com.tisitha.pipeline.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.stereotype.Service;

@Service
public class GeminiChatServiceImp implements GeminiChatService {

    private final Client client = new Client();

    public String getAiResponse(String message) {
        String intro = """
                You are using as a parser of application file. you need to extract information from following data then return response as json like this. If couldn't find keep it empty.
                In here links means their websites,git hub and social media profile links.
                {
                  "fullName": "",
                  "applyingJobTitle": "",
                  "dob": "",
                  "cvEmail": "",
                  "location": "",
                  "links": [],
                  "educations": [
                    {
                      "accreditation": "",
                      "organization": "",
                      "grade": ""
                    }
                  ],
                  "experience": [
                    {
                      "jobTitle": "",
                      "organization": "",
                      "dates": ""
                    }
                  ],
                  "totalYearsExperience": "",
                  "skills": [],
                  "projects": [],
                  "achievements": []
                }
                
                data-
                """;

        GenerateContentConfig config =
                GenerateContentConfig.builder()
                        .responseMimeType("application/json")
                        .candidateCount(1)
                        .build();

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash",
                        intro + message,
                        config);

        return response.text();

    }

}