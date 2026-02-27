package com.fsdm.examsmanagement.strategy;

import com.fsdm.examsmanagement.model.QFillInBlank;
import com.fsdm.examsmanagement.model.Questioner;
import jakarta.ejb.Stateless;

@Stateless
public class CreateQFillBlank implements CreateQuestioner{
    @Override
    public Questioner construireQuestioner(String line) {
        QFillInBlank qFillInBlank = new QFillInBlank();
        qFillInBlank.setQuestion(line);
        return  qFillInBlank;
    }
}
