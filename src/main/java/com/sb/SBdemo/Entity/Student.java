package com.sb.SBdemo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "STUDENT_TABLE")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID")
    private int id;

    @Column(name = "STUDENT_NAME")
    private String name;

    @Column(name = "STUDENT_MAIL")
    private String mail;

    @Column(name = "STUDENT_CITY")
    private String city;

    @Column(name = "STUDENT_PASSWORD")
    private String password;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Post> post = new ArrayList<>();
}
