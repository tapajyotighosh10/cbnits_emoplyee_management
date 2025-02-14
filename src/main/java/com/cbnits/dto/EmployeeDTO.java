package com.cbnits.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class EmployeeDTO {
    private String name;
    private String role;
    private LocalDate dateOfJoining;
    private LocalDate dateOfEmployment;
    private String street;
    private int experience;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private boolean employeeStatus;
    private String managerName;

    private List<ProjectDTO> employeeProjects;
    private List<AchievementDTO> achievements;
    private List<CertificationDTO> certifications;
    private List<EducationDTO> education;
    private List<ExpertiseDTO> expertise;
    private List<IncrementDTO> increments;
    private List<PerformanceReviewDTO> performanceReviews;
    private List<ResourceAccessDTO> resourceAccess;
}
