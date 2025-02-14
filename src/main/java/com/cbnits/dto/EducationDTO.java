package com.cbnits.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class EducationDTO {
    private String degree;
    private String institution;
    private LocalDate yearOfCompletion;
}
