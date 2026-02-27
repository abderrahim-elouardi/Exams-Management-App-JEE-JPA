package com.fsdm.examsmanagement.strategy;

import com.fsdm.examsmanagement.model.Questioner;

public interface CreateQuestioner {
    Questioner construireQuestioner(String line);
}
