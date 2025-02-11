package com.cbnits.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "resource_access")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accessId;
    private String accessType;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
