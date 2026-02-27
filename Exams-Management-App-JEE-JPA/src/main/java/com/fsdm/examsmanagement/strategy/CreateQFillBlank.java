package com.fsdm.examsmanagement.strategy;

import com.fsdm.examsmanagement.dao.question.QFillBlankDAOImp;
import com.fsdm.examsmanagement.model.QFillInBlank;
import com.fsdm.examsmanagement.model.Questioner;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class CreateQFillBlank implements CreateQuestioner{
    @EJB
    private QFillBlankDAOImp qFillBlankDAOImp;
    @Override
    public Questioner construireQuestioner(String line) {
        QFillInBlank qFillInBlank = new QFillInBlank();
        qFillInBlank.setQuestion(line);
        return  qFillInBlank;
    }
}
