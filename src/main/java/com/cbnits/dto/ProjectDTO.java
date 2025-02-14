package com.cbnits.dto;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ProjectDTO {
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;
}
