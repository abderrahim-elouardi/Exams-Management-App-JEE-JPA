package com.fsdm.examsmanagement.dao.User;

import com.fsdm.examsmanagement.model.User;
import com.fsdm.examsmanagement.dao.core.GeneriqueDAO;

public interface UserDAO extends GeneriqueDAO<User, Long> {
    public User findByEmailAndPassword(String email , String password);
}
