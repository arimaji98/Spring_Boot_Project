package com.sb.SBdemo.Service;

import com.sb.SBdemo.Dto.CategoryDto;
import com.sb.SBdemo.Entity.Category;
import com.sb.SBdemo.Exceptions.NoSuchElementExceptionHandler;
import com.sb.SBdemo.Repo.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<CategoryDto> create(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryRepo.save(category);
        return new ResponseEntity("CATEGORY DATA SAVED SUCCESSFULLY.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CategoryDto>> getAll() {
        List<CategoryDto> categoryListDto = categoryRepo.findAll().stream()
                                                        .map(dto -> modelMapper.map(dto, CategoryDto.class))
                                                        .collect(Collectors.toList());
        if (categoryListDto.isEmpty()) {
            return new ResponseEntity("CATEGORY RECORD NOT FOUND", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.of(Optional.of(categoryListDto));
    }

    @Override
    public ResponseEntity<CategoryDto> get(int catId) {
        Category category = categoryRepo.findById(catId).orElseThrow(() -> new NoSuchElementExceptionHandler("Category",
                                                                                                             "ID",
                                                                                                             catId));
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return ResponseEntity.of(Optional.of(categoryDto));
    }

    @Override
    public ResponseEntity<ApiResponse> update(CategoryDto category) {
        Category id = categoryRepo.findById(category.getCatId()).orElseThrow(() -> new NoSuchElementExceptionHandler(
                "Student", "ID", category.getCatId()));

        Category cat = modelMapper.map(category, Category.class);
        categoryRepo.save(cat);
        return new ResponseEntity(new ApiResponse("Category record with ID " + category.getCatId() + " updated " +
                                                  "successfully", true), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse> remove(int catId) {
        Category id = categoryRepo.findById(catId).orElseThrow(() -> new NoSuchElementExceptionHandler("Category",
                                                                                                       "ID", catId));
        categoryRepo.deleteById(catId);
        return new ResponseEntity(new ApiResponse("Category record with ID " + catId + " deleted successfully", true)
                , HttpStatus.OK);
    }
}
