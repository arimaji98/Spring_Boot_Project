package com.sb.SBdemo.Service;

import com.sb.SBdemo.Dto.PostDto;
import com.sb.SBdemo.Entity.Category;
import com.sb.SBdemo.Entity.Post;
import com.sb.SBdemo.Entity.Student;
import com.sb.SBdemo.Exceptions.NoSuchElementExceptionHandler;
import com.sb.SBdemo.Repo.CategoryRepo;
import com.sb.SBdemo.Repo.PostRepo;
import com.sb.SBdemo.Repo.StudentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public ResponseEntity<PostDto> create(PostDto postDto, int studentId, int catId) {
        Category category = categoryRepo.findById(catId).orElseThrow(() -> new NoSuchElementExceptionHandler(
                "Category", "ID", catId));
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new NoSuchElementExceptionHandler(
                "Student", "ID", studentId));
        Post postAdd = modelMapper.map(postDto, Post.class);
        if (postAdd.getPostImage() == null) postAdd.setPostImage("default.png");
        if (postAdd.getPostAddedDate() == null) postAdd.setPostAddedDate(new Date());
        postAdd.setCategory(category);
        postAdd.setStudent(student);
        postRepo.save(postAdd);
        PostDto dto = modelMapper.map(postAdd, PostDto.class);
        return new ResponseEntity("POST DATA SAVED SUCCESSFULLY.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostResponse> getAll(int pageNumber, int pageSize, String sortBy, String sortByKey) {
        Sort sort = sortBy.equalsIgnoreCase("asc") ? Sort.by(sortByKey).ascending() : Sort.by(sortByKey).descending();
        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> pagePost = postRepo.findAll(p);

        List<PostDto> postDtoList =
                pagePost.getContent().stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        if (postDtoList.isEmpty()) {
            return new ResponseEntity("NO POST RECORD AVAILABLE.", HttpStatus.NOT_FOUND);
        }

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return ResponseEntity.of(Optional.of(postResponse));
    }

    @Override
    public ResponseEntity<PostDto> get(int postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new NoSuchElementExceptionHandler("Post", "ID",
                                                                                                  postId));
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return ResponseEntity.of(Optional.of(postDto));
    }

    @Override
    public ResponseEntity<List<PostDto>> getAllByCategory(int catId) {
        Category category = categoryRepo.findById(catId).orElseThrow(() -> new NoSuchElementExceptionHandler(
                "Category", "ID", catId));
        List<Post> postList = postRepo.findByCategory(category);
        if (postList.isEmpty()) {
            return new ResponseEntity("POST RECORD NOT FOUND FOR CATEGORY ID: " + catId, HttpStatus.NOT_FOUND);
        }

        List<PostDto> postDtoList =
                postList.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return ResponseEntity.of(Optional.of(postDtoList));
    }

    @Override
    public ResponseEntity<List<PostDto>> getAllByStudent(int studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new NoSuchElementExceptionHandler(
                "Student", "ID", studentId));
        List<Post> postList = postRepo.findByStudent(student);
        if (postList.isEmpty()) {
            return new ResponseEntity("POST RECORD NOT FOUND FOR STUDENT ID: " + studentId, HttpStatus.NOT_FOUND);
        }

        List<PostDto> postDtoList =
                postList.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return ResponseEntity.of(Optional.of(postDtoList));
    }

    @Override
    public ResponseEntity<ApiResponse> update(PostDto postDto, int postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new NoSuchElementExceptionHandler
                ("Post", "ID", postId));

        if (!postDto.getPostTitle().equals(post.getPostTitle())) post.setPostTitle(postDto.getPostTitle());
        if (!postDto.getPostContent().equals(post.getPostContent())) post.setPostContent(postDto.getPostContent());
        if (!postDto.getPostImage().equals(post.getPostImage())) post.setPostImage(postDto.getPostImage());
        post.setPostAddedDate(new Date());

        postRepo.save(post);
        return new ResponseEntity(new ApiResponse("Post record with ID " + postId + " updated " +
                                                  "successfully", true), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse> remove(int postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new NoSuchElementExceptionHandler("Post", "ID",
                                                                                                  postId));
        postRepo.deleteById(postId);
        return new ResponseEntity(new ApiResponse("Post record with ID " + postId + " deleted successfully",
                                                  true), HttpStatus.OK);
    }
}
