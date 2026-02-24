package com.fsdm.examsmanagement.dao.exam;

import com.fsdm.examsmanagement.model.Exam;
import com.fsdm.examsmanagement.model.User;
import com.fsdm.examsmanagement.dao.GeneriqueDAO;

public interface ExamDAO extends GeneriqueDAO<Exam, Long> {
    public User findByEmailAndPassword(String email , String password);
}
