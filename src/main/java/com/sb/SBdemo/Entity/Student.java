package com.sb.SBdemo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID")
    private int id;

    @Column(name = "STUDENT_NAME")
    @NotEmpty(message = "Name should not be null or blank.")
    @Size(min = 4, message = "At least 4 characters required.")
    private String name;

    @Column(name = "STUDENT_MAIL")
    @Email(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Email is Invalid")
    private String mail;

    @Column(name = "STUDENT_CITY")
    @NotEmpty(message = "City should not be null or blank.")
    @Size(min = 4, message = "At least 4 characters required.")
    private String city;

    @Column(name = "STUDENT_PASSWORD")
    @Size(min = 8, max = 20, message = "At least 8 characters and a maximum of 20 characters required.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
            message = "At least one digit, one lowercase, one uppercase and one special character required.")
    private String password;
}
