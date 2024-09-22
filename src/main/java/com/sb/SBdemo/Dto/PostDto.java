package com.sb.SBdemo.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDto {
    private int postId;

    @NotBlank(message = "Post title should not be null or empty.")
    private String postTitle;

    @NotBlank(message = "Post content should not be null or empty.")
    private String postContent;

    //    @NotBlank(message = "Post image should not be null or empty.")
    private String postImage;

    //    @NotBlank(message = "Post date should not be null or empty.")
    private Date postAddedDate;

    private CategoryDto category;

    private StudentDto student;
}
