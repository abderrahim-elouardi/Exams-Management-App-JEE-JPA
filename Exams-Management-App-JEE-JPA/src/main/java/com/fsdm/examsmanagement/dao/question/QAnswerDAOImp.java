package com.fsdm.examsmanagement.dao.question;

import com.fsdm.examsmanagement.dao.core.AbstractGeneriqueDAO;
import com.fsdm.examsmanagement.model.QCMAnswer;
import jakarta.ejb.Stateless;

@Stateless
public class QAnswerDAOImp extends AbstractGeneriqueDAO<QCMAnswer, Long> {
    public QAnswerDAOImp(){
        super(QCMAnswer.class, "id");
    }
}
