package com.cbnits.controller;

import com.cbnits.dto.EmployeeDTO;
import com.cbnits.dto.ProjectRequirementDTO;
import com.cbnits.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.ok(createdEmployee);
    }

    @GetMapping("/searching")
    public ResponseEntity<List<EmployeeDTO>> submitProjectRequirement(@RequestBody ProjectRequirementDTO projectRequirementDTO) {
        List<EmployeeDTO> suggestions = employeeService.findMatchingEmployees(projectRequirementDTO);
        return ResponseEntity.ok(suggestions);
    }
}
