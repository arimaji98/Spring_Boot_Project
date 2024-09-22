package com.sb.SBdemo.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDto {
    private int id;

    @NotBlank(message = "Name should not be null or blank.")
    @Size(min = 4, message = "At least 4 characters required.")
    private String name;

    @Email(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Email is Invalid")
    private String mail;

    @NotBlank(message = "City should not be null or blank.")
    @Size(min = 4, message = "At least 4 characters required.")
    private String city;

    @Size(min = 8, max = 20, message = "At least 8 characters and a maximum of 20 characters required.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
            message = "At least one digit, one lowercase, one uppercase and one special character required.")
    private String password;
}
