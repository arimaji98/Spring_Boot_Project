package com.sb.SBdemo.Service;

import com.sb.SBdemo.Dto.CategoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    // create
    ResponseEntity<CategoryDto> create(CategoryDto categoryDto);

    // get All
    ResponseEntity<List<CategoryDto>> getAll();

    // get with id
    ResponseEntity<CategoryDto> get(int catId);

    // update
    ResponseEntity<ApiResponse> update(CategoryDto categoryDto);

    // delete
    ResponseEntity<ApiResponse> remove(int catId);
}
