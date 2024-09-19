package com.sb.SBdemo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "CATEGORY_TABLE")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private int catId;

    @Column(name = "CATEGORY_TITLE")
    @NotBlank(message = "Category title should not be null or empty")
    private String catTitle;

    @Column(name = "CATEGORY_DESCRIPTION")
    @NotBlank(message = "Category description should not be null or empty")
    private String catDescription;
}
