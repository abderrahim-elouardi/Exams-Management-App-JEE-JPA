package com.fsdm.examsmanagement.repositories;

import com.fsdm.examsmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
