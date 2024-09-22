package com.sb.SBdemo.Controller;

import com.sb.SBdemo.Dto.PostDto;
import com.sb.SBdemo.Service.ApiResponse;
import com.sb.SBdemo.Service.PostResponse;
import com.sb.SBdemo.Service.PostServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @PostMapping("/student/{studentId}/category/{catId}/posts")
    public ResponseEntity<PostDto> addPost(@Valid @RequestBody PostDto postDto, @PathVariable int studentId,
                                           @PathVariable int catId) {
        return postService.create(postDto, studentId, catId);
    }

    @GetMapping("post/getPosts")
    public ResponseEntity<PostResponse> getPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "asc", required = false) String sortBy,
            @RequestParam(value = "sortByKey", defaultValue = "postId", required = false) String sortByKey) {
        return postService.getAll(pageNumber, pageSize, sortBy, sortByKey);
    }

    @GetMapping("post/getPost/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable int postId) {
        return postService.get(postId);
    }

    @GetMapping("/category/{catId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable int catId) {
        return postService.getAllByCategory(catId);
    }

    @GetMapping("/student/{studentId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByStudent(@PathVariable int studentId) {
        return postService.getAllByStudent(studentId);
    }

    @PutMapping("/post/update/{postId}")
    public ResponseEntity<ApiResponse> updateCategory(@Valid @RequestBody PostDto postDto,
                                                      @PathVariable Integer postId) {
        return postService.update(postDto, postId);
    }

    @DeleteMapping("/post/delete/{id}")
    public ResponseEntity<ApiResponse> removeCategory(@PathVariable Integer id) {
        return postService.remove(id);
    }
}
