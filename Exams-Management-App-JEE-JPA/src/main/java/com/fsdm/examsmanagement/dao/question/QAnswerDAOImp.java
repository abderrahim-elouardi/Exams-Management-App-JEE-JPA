package com.fsdm.examsmanagement.dao.question;

import com.fsdm.examsmanagement.dao.core.AbstractGeneriqueDAO;
<<<<<<< HEAD
import com.fsdm.examsmanagement.model.QCMAnswer;

public class QAnswerDAOImp extends AbstractGeneriqueDAO<QCMAnswer, Long> {
=======
import com.fsdm.examsmanagement.model.QAnswer;
import jakarta.ejb.Stateless;

@Stateless
public class QAnswerDAOImp extends AbstractGeneriqueDAO<QAnswer, Long> {
>>>>>>> d7e05ee (add upload file and interface chose prepared exam)
    public QAnswerDAOImp(){
        super(QCMAnswer.class, "id");
    }
}
