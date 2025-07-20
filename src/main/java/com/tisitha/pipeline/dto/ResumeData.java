package com.tisitha.pipeline.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResumeData {

    private String fullName;

    private String applyingJobTitle;

    private String dob;

    private String cvEmail;

    private String location;

    private List<String> links;

    private List<Education> educations;

    private List<Experience> experience;

    private String totalYearsExperience;

    private List<String> skills;

    private List<String> projects;

    private List<String> achievements;
}
