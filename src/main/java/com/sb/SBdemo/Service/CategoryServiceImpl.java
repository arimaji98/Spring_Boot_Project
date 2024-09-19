package com.sb.SBdemo.Service;

import com.sb.SBdemo.Entity.Category;
import com.sb.SBdemo.Entity.Student;
import com.sb.SBdemo.Exceptions.NoSuchElementExceptionHandler;
import com.sb.SBdemo.Repo.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements  CategoryService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<Category> create(Category category) {
        categoryRepo.save(category);
        return new ResponseEntity("CATEGORY DATA SAVED SUCCESSFULLY.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categoryList = categoryRepo.findAll();
        if (categoryList.isEmpty()) {
            return new ResponseEntity("CATEGORY RECORD NOT FOUND", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.of(Optional.of(categoryList));
    }

    @Override
    public ResponseEntity<Category> get(int catId) {
        Category id = categoryRepo.findById(catId).orElseThrow(() -> new NoSuchElementExceptionHandler("Category", "ID", catId));
        return ResponseEntity.of(Optional.of(id));
    }

    @Override
    public ResponseEntity<ApiResponse> update(Category category) {
        Category id = categoryRepo.findById(category.getCatId()).orElseThrow(() -> new NoSuchElementExceptionHandler("Student", "ID", category.getCatId()));

        Category cat = modelMapper.map(category, Category.class);
        categoryRepo.save(cat);
        return new ResponseEntity(new ApiResponse("Category record with ID " + category.getCatId() + " updated successfully", true), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse> remove(int catId) {
        Category id = categoryRepo.findById(catId).orElseThrow(() -> new NoSuchElementExceptionHandler("Category", "ID", catId));
        categoryRepo.deleteById(catId);
        return new ResponseEntity(new ApiResponse("Category record with ID " + catId + " deleted successfully", true), HttpStatus.OK);
    }
}
