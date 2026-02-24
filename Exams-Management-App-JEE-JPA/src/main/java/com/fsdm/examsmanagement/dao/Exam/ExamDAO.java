package com.fsdm.examsmanagement.dao.Exam;

import com.fsdm.examsmanagement.model.Exam;
import com.fsdm.examsmanagement.model.User;
import com.fsdm.examsmanagement.dao.core.GeneriqueDAO;

public interface ExamDAO extends GeneriqueDAO<Exam, Long> {
    public User findByEmailAndPassword(String email , String password);
}
