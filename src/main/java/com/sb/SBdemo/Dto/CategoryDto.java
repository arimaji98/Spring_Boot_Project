package com.sb.SBdemo.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDto {
    private int catId;

    @NotBlank(message = "Category title should not be null or empty")
    private String catTitle;

    @NotBlank(message = "Category description should not be null or empty")
    private String catDescription;
}
