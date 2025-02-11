package com.cbnits.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "expertise")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expertise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expertiseId;
    private String skill;
    private String proficiencyLevel;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
