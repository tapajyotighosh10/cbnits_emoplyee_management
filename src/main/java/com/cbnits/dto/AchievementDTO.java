package com.cbnits.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class AchievementDTO {
    private String achievementName;
    private LocalDate date;
}
