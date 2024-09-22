package com.sb.SBdemo.Service;

import com.sb.SBdemo.Dto.PostDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    // create
    ResponseEntity<PostDto> create(PostDto postDto, int studentId, int catId);

    // get All
    ResponseEntity<PostResponse> getAll(int pageNumber, int pageSize, String sortBy, String sortByKey);

    // get with id
    ResponseEntity<PostDto> get(int postId);

    // get All by Category
    ResponseEntity<List<PostDto>> getAllByCategory(int catId);

    // get All by Student
    ResponseEntity<List<PostDto>> getAllByStudent(int studentId);

    // update
    ResponseEntity<ApiResponse> update(PostDto postDto, int postId);

    // delete
    ResponseEntity<ApiResponse> remove(int postId);
}
