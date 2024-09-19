package com.sb.SBdemo.Service;

import com.sb.SBdemo.Entity.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    // create
    ResponseEntity<Category> create(Category category);

    // get All
    ResponseEntity<List<Category>> getAll();

    // get with id
    ResponseEntity<Category> get(int catId);

    // update
    ResponseEntity<ApiResponse> update(Category category);

    // delete
    ResponseEntity<ApiResponse> remove(int catId);
}
