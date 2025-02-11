package com.cbnits.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "increments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Increment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long incrementId;
    private Date date;
    private float percentage;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
