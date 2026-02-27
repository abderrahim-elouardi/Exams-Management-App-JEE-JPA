package com.fsdm.examsmanagement.strategy;

import com.fsdm.examsmanagement.model.QCM;
import com.fsdm.examsmanagement.model.Questioner;
import jakarta.ejb.Stateless;

@Stateless
public class CreateQCM implements CreateQuestioner{
    @Override
    public Questioner construireQuestioner(String line) {
        String[] splitQuestion = line.split("\\|");
        String question = splitQuestion[0];
        String[] responses = splitQuestion[1].split("\\,");
        String numberOfResponse = splitQuestion[2];
        QCM qcm = new QCM();
        qcm.setQuestion(question);
        return null;
    }
}
