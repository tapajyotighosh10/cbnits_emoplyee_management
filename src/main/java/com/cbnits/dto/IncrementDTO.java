package com.cbnits.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class IncrementDTO {
    private LocalDate date;
    private float percentage;
}