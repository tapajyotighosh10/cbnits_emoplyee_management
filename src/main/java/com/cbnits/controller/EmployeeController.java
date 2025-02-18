package com.cbnits.controller;

import com.cbnits.dto.EmployeeDTO;
import com.cbnits.dto.ProjectRequirementDTO;
import com.cbnits.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Employees APIs", description = "REST APIs for CBNITS Employees")
@RestController
@RequestMapping("/api/employees")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Create Employee REST API", description = "REST API to create new employee  for CBNITS", tags = {"Employees APIs"})
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    }
    )
    @PostMapping("/create")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        log.info("Received request to create employee: {}", employeeDTO.getName());
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
        log.info("Employee created successfully with Name: {}", createdEmployee.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }


    @Operation(summary = "Searching Employee REST API", description = "REST API to search for employee based on experience and skill", tags = {"Employees APIs"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status SUCCESS"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error")
    })
    @GetMapping("/searching")
    public ResponseEntity<List<EmployeeDTO>> submitProjectRequirement(@RequestBody ProjectRequirementDTO projectRequirementDTO) {
        log.info("Received request to search employees for project requirements: Min Experience - {}, Required Skills - {}",
                projectRequirementDTO.getMinExperience(), projectRequirementDTO.getRequiredSkills());
        List<EmployeeDTO> suggestions = employeeService.findMatchingEmployees(projectRequirementDTO);
        log.info("Found {} matching employees", suggestions.size());
        return ResponseEntity.ok(suggestions);
    }
}
