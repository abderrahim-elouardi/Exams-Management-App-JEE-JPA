package com.fsdm.examsmanagement.dao.question;

import com.fsdm.examsmanagement.dao.core.AbstractGeneriqueDAO;
import com.fsdm.examsmanagement.model.QFillInBlank;

public class QFillBlankDAOImp extends AbstractGeneriqueDAO<QFillInBlank, Long> {
    public QFillBlankDAOImp(){
        super(QFillInBlank.class,"id");
    }
}
