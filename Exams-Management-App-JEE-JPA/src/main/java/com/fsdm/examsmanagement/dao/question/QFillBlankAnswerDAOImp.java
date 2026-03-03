package com.fsdm.examsmanagement.dao.question;

import com.fsdm.examsmanagement.dao.core.AbstractGeneriqueDAO;
import com.fsdm.examsmanagement.model.QCMAnswer;
import com.fsdm.examsmanagement.model.QFillInBlank;
import com.fsdm.examsmanagement.model.QFillInBlankAnswer;
import jakarta.ejb.Stateless;

@Stateless
public class QFillBlankAnswerDAOImp extends AbstractGeneriqueDAO<QFillInBlankAnswer, Long> {
    public QFillBlankAnswerDAOImp(){
        super(QFillInBlankAnswer.class, "id");
    }
}
