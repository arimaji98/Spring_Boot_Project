package com.sb.SBdemo.Repo;

import com.sb.SBdemo.Entity.Category;
import com.sb.SBdemo.Entity.Post;
import com.sb.SBdemo.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByCategory(Category category);
    List<Post> findByStudent(Student student);
}
