package com.fsdm.examsmanagement.dao.question;

import com.fsdm.examsmanagement.dao.core.AbstractGeneriqueDAO;
import com.fsdm.examsmanagement.model.Questioner;
import jakarta.ejb.Stateless;

@Stateless
public class QuestionerDAOImp extends AbstractGeneriqueDAO<Questioner, Long> {
    public QuestionerDAOImp(){
        super(Questioner.class, "id");
    }
}
