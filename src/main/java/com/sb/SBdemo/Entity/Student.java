package com.sb.SBdemo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "STUDENT_TABLE")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STUDENT_ID")
    private int id;

    @Column(name = "STUDENT_NAME")
    private String name;

    @Column(name = "STUDENT_CITY")
    private String city;
}
