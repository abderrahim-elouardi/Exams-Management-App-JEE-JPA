package com.fsdm.examsmanagement.strategy;

import com.fsdm.examsmanagement.model.Questioner;
import jakarta.ejb.Stateless;

@Stateless
public class CreateQShort implements CreateQuestioner{
    @Override
    public Questioner construireQuestioner(String line) {
        String[] splitQuestion = line.split("\\|");
        String question = splitQuestion[0];
        String[] responses = splitQuestion[1].split("\\,,");
        return null;
    }
}
