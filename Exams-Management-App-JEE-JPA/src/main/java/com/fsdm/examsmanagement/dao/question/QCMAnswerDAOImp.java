package com.fsdm.examsmanagement.dao.question;

import com.fsdm.examsmanagement.dao.core.AbstractGeneriqueDAO;
import com.fsdm.examsmanagement.model.QCMAnswer;
import jakarta.ejb.Stateless;

@Stateless
public class QCMAnswerDAOImp extends AbstractGeneriqueDAO<QCMAnswer, Long> {
    public QCMAnswerDAOImp(){
        super(QCMAnswer.class, "id");
    }
}
