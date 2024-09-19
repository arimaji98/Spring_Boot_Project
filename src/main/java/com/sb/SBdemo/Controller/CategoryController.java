package com.sb.SBdemo.Controller;

import com.sb.SBdemo.Entity.Category;
import com.sb.SBdemo.Entity.Student;
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
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category) {
        return categoryService.create(category);
    }

    @GetMapping("/getCategories")
    public ResponseEntity<List<Category>> getCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/getCategory/{catId}")
    public ResponseEntity<Category> getCategory(@PathVariable int catId) {
        return categoryService.get(catId);
    }

    @PutMapping("/update")
    public  ResponseEntity<ApiResponse> updateCategory(@Valid @RequestBody Category category) {
        return categoryService.update(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> removeCategory(@PathVariable Integer id) {
        return categoryService.remove(id);
    }
}
