package com.cbnits.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CertificationDTO {
    private String certificationName;
    private LocalDate issuedDate;
    private LocalDate expiryDate;
}
