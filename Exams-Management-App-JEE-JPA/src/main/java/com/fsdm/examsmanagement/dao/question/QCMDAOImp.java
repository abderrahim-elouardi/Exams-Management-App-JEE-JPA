package com.fsdm.examsmanagement.dao.question;

import com.fsdm.examsmanagement.dao.core.AbstractGeneriqueDAO;
import com.fsdm.examsmanagement.model.QCM;

public class QCMDAOImp extends AbstractGeneriqueDAO<QCM, Long> {
    public QCMDAOImp(){
        super(QCM.class, "id");
    }
}
