package com.sb.SBdemo.Repo;

import com.sb.SBdemo.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    public Student findByName(String name);
}
