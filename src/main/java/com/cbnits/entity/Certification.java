package com.cbnits.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "certifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long certificationId;
    private String certificationName;
    private LocalDate issuedDate;
    private LocalDate expiryDate;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
}
