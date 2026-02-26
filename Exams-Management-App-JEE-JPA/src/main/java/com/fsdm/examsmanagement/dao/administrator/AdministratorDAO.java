package com.fsdm.examsmanagement.dao.administrator;

import com.fsdm.examsmanagement.model.Administrator;
import com.fsdm.examsmanagement.model.User;
import com.fsdm.examsmanagement.dao.core.GeneriqueDAO;

public interface AdministratorDAO extends GeneriqueDAO<Administrator, Long> {
    public Administrator findByEmailAndPassword(String email , String password);
}
