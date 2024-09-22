package com.sb.SBdemo.Controller;

import com.sb.SBdemo.Dto.CategoryDto;
import com.sb.SBdemo.Service.ApiResponse;
import com.sb.SBdemo.Service.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryServiceImpl categoryService;

    @PostMapping("/addCategory")
    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return categoryService.create(categoryDto);
    }

    @GetMapping("/getCategories")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/getCategory/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable int catId) {
        return categoryService.get(catId);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return categoryService.update(categoryDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> removeCategory(@PathVariable Integer id) {
        return categoryService.remove(id);
    }
}
