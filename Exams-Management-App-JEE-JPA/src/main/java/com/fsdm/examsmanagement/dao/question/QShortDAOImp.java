package com.fsdm.examsmanagement.dao.question;

import com.fsdm.examsmanagement.dao.core.AbstractGeneriqueDAO;
import com.fsdm.examsmanagement.model.QShort;
import jakarta.ejb.Stateless;

@Stateless
public class QShortDAOImp extends AbstractGeneriqueDAO<QShort, Long> {
    public QShortDAOImp(){
        super(QShort.class, "id");
    }
}
