package com.fsdm.examsmanagement.dao.question;

import com.fsdm.examsmanagement.dao.core.AbstractGeneriqueDAO;
import com.fsdm.examsmanagement.model.QCMAnswer;
import com.fsdm.examsmanagement.model.QShortAnswer;
import jakarta.ejb.Stateless;

@Stateless
public class QShortAnswerDAOImp extends AbstractGeneriqueDAO<QShortAnswer, Long> {
    public QShortAnswerDAOImp(){
        super(QShortAnswer.class, "id");
    }
}
