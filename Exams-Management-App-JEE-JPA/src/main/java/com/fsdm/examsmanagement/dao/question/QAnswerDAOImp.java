package com.fsdm.examsmanagement.dao.question;

import com.fsdm.examsmanagement.dao.core.AbstractGeneriqueDAO;
import com.fsdm.examsmanagement.model.QAnswer;

public class QAnswerDAOImp extends AbstractGeneriqueDAO<QAnswer, Long> {
    public QAnswerDAOImp(){
        super(QAnswer.class, "id");
    }
}
