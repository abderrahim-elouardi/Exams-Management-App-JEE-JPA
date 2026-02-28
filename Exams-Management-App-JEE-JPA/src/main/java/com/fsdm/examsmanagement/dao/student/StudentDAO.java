package com.fsdm.examsmanagement.dao.student;

import com.fsdm.examsmanagement.model.Student;
import com.fsdm.examsmanagement.model.User;
import com.fsdm.examsmanagement.dao.core.GeneriqueDAO;

public interface StudentDAO extends GeneriqueDAO<Student, Long> {
    public Student findByEmailAndPassword(String email , String password);
    public Student findById(Long id);
    public long countStudents();
}
