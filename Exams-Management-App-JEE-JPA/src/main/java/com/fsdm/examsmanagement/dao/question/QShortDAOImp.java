package com.fsdm.examsmanagement.dao.question;

import com.fsdm.examsmanagement.dao.core.AbstractGeneriqueDAO;
import com.fsdm.examsmanagement.model.QShort;

public class QShortDAOImp extends AbstractGeneriqueDAO<QShort, Long> {
    public QShortDAOImp(){
        super(QShort.class, "id");
    }
}
