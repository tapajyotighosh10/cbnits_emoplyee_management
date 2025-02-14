package com.cbnits.service;

import com.cbnits.dto.EmployeeDTO;
import com.cbnits.dto.ProjectRequirementDTO;
import com.cbnits.entity.*;
import com.cbnits.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);

        // Convert Projects
        List<Project> employeeProjects = employeeDTO.getEmployeeProjects().stream()
                .map(dto -> {
                    Project project = modelMapper.map(dto, Project.class);

                    project.setEmployee(employee);
                    return project;

                }).collect(Collectors.toList());

        employee.setEmployeeProjects(employeeProjects);

        // Convert Achievements
        List<Achievement> achievements = employeeDTO.getAchievements().stream()
                .map(dto -> {
                    Achievement achievement = modelMapper.map(dto, Achievement.class);
                    achievement.setEmployee(employee);
                    return achievement;
                }).collect(Collectors.toList());
        employee.setAchievements(achievements);

        // Convert Certifications
        List<Certification> certifications = employeeDTO.getCertifications().stream()
                .map(dto -> {
                    Certification certification = modelMapper.map(dto, Certification.class);
                    certification.setEmployee(employee);
                    return certification;
                }).collect(Collectors.toList());
        employee.setCertifications(certifications);

        // Convert Education
        List<Education> educationList = employeeDTO.getEducation().stream()
                .map(dto -> {
                    Education edu = modelMapper.map(dto, Education.class);
                    edu.setEmployee(employee);
                    return edu;
                }).collect(Collectors.toList());

        employee.setEducationList(educationList);

        // Convert Expertise
        List<Expertise> expertiseList = employeeDTO.getExpertise().stream()
                .map(dto -> {
                    Expertise expertise = modelMapper.map(dto, Expertise.class);
                    expertise.setEmployee(employee);
                    return expertise;
                }).collect(Collectors.toList());
        employee.setExpertiseList(expertiseList);

        // Convert Increments
        List<Increment> increments = employeeDTO.getIncrements().stream()
                .map(dto -> {
                    Increment increment = modelMapper.map(dto, Increment.class);
                    increment.setEmployee(employee);
                    return increment;
                }).collect(Collectors.toList());
        employee.setIncrements(increments);

        // Convert Performance Reviews
        List<PerformanceReview> reviews = employeeDTO.getPerformanceReviews().stream()
                .map(dto -> {
                    PerformanceReview review = modelMapper.map(dto, PerformanceReview.class);
                    review.setEmployee(employee);
                    return review;
                }).collect(Collectors.toList());
        employee.setPerformanceReviews(reviews);

        // Convert Resource Access
        List<ResourceAccess> resourceAccessList = employeeDTO.getResourceAccess().stream()
                .map(dto -> {
                    ResourceAccess access = modelMapper.map(dto, ResourceAccess.class);
                    access.setEmployee(employee);
                    return access;
                }).collect(Collectors.toList());
        employee.setResourceAccessList(resourceAccessList);

        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }


    public List<EmployeeDTO> findMatchingEmployees(ProjectRequirementDTO projectRequirementDTO) {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .filter(emp -> calculateExperience(emp.getDateOfJoining()) >= projectRequirementDTO.getMinExperience()) // Compute experience
                .filter(emp -> emp.getExpertiseList().stream()
                        .anyMatch(exp -> projectRequirementDTO.getRequiredSkills().contains(exp.getSkill())))
                .map(emp -> {
                    EmployeeDTO dto = modelMapper.map(emp, EmployeeDTO.class);
                    dto.setExperience(calculateExperience(emp.getDateOfJoining())); // Set calculated experience
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private int calculateExperience(LocalDate dateOfJoining) {
        if (dateOfJoining == null) return 0;
        return Period.between(dateOfJoining, LocalDate.now()).getYears();
    }

}
