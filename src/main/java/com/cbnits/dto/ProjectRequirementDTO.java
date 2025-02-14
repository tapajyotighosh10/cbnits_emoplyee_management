package com.cbnits.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProjectRequirementDTO {
    private String projectName;
    private List<String> requiredSkills;
    private int minExperience;
}
