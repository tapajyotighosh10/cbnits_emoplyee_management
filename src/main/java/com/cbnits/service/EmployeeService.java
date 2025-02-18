package com.cbnits.service;

import com.cbnits.dto.EmployeeDTO;
import com.cbnits.dto.ProjectRequirementDTO;
import com.cbnits.entity.*;
import com.cbnits.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {

        log.info("Creating a new employee: {}", employeeDTO.getName());
        Employee employee = modelMapper.map(employeeDTO, Employee.class);

        log.debug("Mapping projects for employee: {}", employeeDTO.getEmployeeProjects().size());
        // Convert Projects
        List<Project> employeeProjects = employeeDTO.getEmployeeProjects().stream()
                .map(dto -> {
                    Project project = modelMapper.map(dto, Project.class);

                    project.setEmployee(employee);
                    return project;

                }).collect(Collectors.toList());

        employee.setEmployeeProjects(employeeProjects);

        log.debug("Mapping achievements for employee: {}", employeeDTO.getAchievements().size());
        // Convert Achievements
        List<Achievement> achievements = employeeDTO.getAchievements().stream()
                .map(dto -> {
                    Achievement achievement = modelMapper.map(dto, Achievement.class);
                    achievement.setEmployee(employee);
                    return achievement;
                }).collect(Collectors.toList());
        employee.setAchievements(achievements);

        log.debug("Mapping certifications for employee: {}", employeeDTO.getCertifications().size());
        // Convert Certifications
        List<Certification> certifications = employeeDTO.getCertifications().stream()
                .map(dto -> {
                    Certification certification = modelMapper.map(dto, Certification.class);
                    certification.setEmployee(employee);
                    return certification;
                }).collect(Collectors.toList());
        employee.setCertifications(certifications);

        log.debug("Mapping education for employee: {}", employeeDTO.getEducation().size());
        // Convert Education
        List<Education> educationList = employeeDTO.getEducation().stream()
                .map(dto -> {
                    Education edu = modelMapper.map(dto, Education.class);
                    edu.setEmployee(employee);
                    return edu;
                }).collect(Collectors.toList());

        employee.setEducationList(educationList);

        log.debug("Mapping expertise for employee: {}", employeeDTO.getExpertise().size());
        // Convert Expertise
        List<Expertise> expertiseList = employeeDTO.getExpertise().stream()
                .map(dto -> {
                    Expertise expertise = modelMapper.map(dto, Expertise.class);
                    expertise.setEmployee(employee);
                    return expertise;
                }).collect(Collectors.toList());
        employee.setExpertiseList(expertiseList);

        log.debug("Mapping increments for employee: {}", employeeDTO.getIncrements().size());
        // Convert Increments
        List<Increment> increments = employeeDTO.getIncrements().stream()
                .map(dto -> {
                    Increment increment = modelMapper.map(dto, Increment.class);
                    increment.setEmployee(employee);
                    return increment;
                }).collect(Collectors.toList());
        employee.setIncrements(increments);

        log.debug("Mapping reviews for employee: {}", employeeDTO.getPerformanceReviews().size());
        // Convert Performance Reviews
        List<PerformanceReview> reviews = employeeDTO.getPerformanceReviews().stream()
                .map(dto -> {
                    PerformanceReview review = modelMapper.map(dto, PerformanceReview.class);
                    review.setEmployee(employee);
                    return review;
                }).collect(Collectors.toList());
        employee.setPerformanceReviews(reviews);

        log.debug("Mapping resource for employee: {}", employeeDTO.getResourceAccess().size());
        // Convert Resource Access
        List<ResourceAccess> resourceAccessList = employeeDTO.getResourceAccess().stream()
                .map(dto -> {
                    ResourceAccess access = modelMapper.map(dto, ResourceAccess.class);
                    access.setEmployee(employee);
                    return access;
                }).collect(Collectors.toList());
        employee.setResourceAccessList(resourceAccessList);

        log.info("Saving employee to database...");
        Employee savedEmployee = employeeRepository.save(employee);
        log.info("Employee saved successfully with ID: {}", savedEmployee.getEmployeeId());
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }


    public List<EmployeeDTO> findMatchingEmployees(ProjectRequirementDTO projectRequirementDTO) {
        log.info("Finding matching employees for project requirements: Min Experience - {}, Required Skills - {}",
                projectRequirementDTO.getMinExperience(), projectRequirementDTO.getRequiredSkills());
        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeDTO> matchingEmployees = employees.stream()
                .filter(emp -> calculateExperience(emp.getDateOfJoining()) >= projectRequirementDTO.getMinExperience())
                .filter(emp -> emp.getExpertiseList().stream()
                        .anyMatch(exp -> projectRequirementDTO.getRequiredSkills().contains(exp.getSkill())))
                .map(emp -> {
                    EmployeeDTO dto = modelMapper.map(emp, EmployeeDTO.class);
                    dto.setExperience(calculateExperience(emp.getDateOfJoining()));
                    return dto;
                })
                .collect(Collectors.toList());

        log.info("Found {} matching employees", matchingEmployees.size());
        return matchingEmployees;
    }

    private int calculateExperience(LocalDate dateOfJoining) {
        if (dateOfJoining == null) return 0;
        int experience = Period.between(dateOfJoining, LocalDate.now()).getYears();
        log.debug("Calculated experience: {} years", experience);
        return experience;
    }

}
