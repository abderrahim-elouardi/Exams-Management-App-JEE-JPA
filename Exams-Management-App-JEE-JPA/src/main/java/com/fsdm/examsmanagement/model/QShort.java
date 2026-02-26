package com.fsdm.examsmanagement.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "qshort")
/**
 * Represents a short-answer question.
 */
public class QShort extends Questioner{

    @OneToOne(mappedBy = "qshort")
    private QShortAnswer answer;
}
