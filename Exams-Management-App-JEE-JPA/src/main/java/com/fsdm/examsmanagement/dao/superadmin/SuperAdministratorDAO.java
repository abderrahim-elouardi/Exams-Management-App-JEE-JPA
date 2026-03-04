package com.fsdm.examsmanagement.dao.superadmin;

import com.fsdm.examsmanagement.dao.core.GeneriqueDAO;
import com.fsdm.examsmanagement.model.Administrator;
import com.fsdm.examsmanagement.model.SuperAdmin;

public interface SuperAdministratorDAO extends GeneriqueDAO<SuperAdmin, Long> {
    public SuperAdmin findByEmailAndPassword(String email , String password);
    public SuperAdmin findByEmail(String email);
}
