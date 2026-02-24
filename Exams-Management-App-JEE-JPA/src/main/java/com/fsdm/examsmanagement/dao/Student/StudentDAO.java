package com.fsdm.examsmanagement.dao.Student;

import com.fsdm.examsmanagement.model.Student;
import com.fsdm.examsmanagement.model.User;
import com.fsdm.examsmanagement.dao.GeneriqueDAO;

public interface StudentDAO extends GeneriqueDAO<Student, Long> {
    public User findByEmailAndPassword(String email , String password);
}
