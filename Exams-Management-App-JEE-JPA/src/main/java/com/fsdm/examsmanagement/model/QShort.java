package com.fsdm.examsmanagement.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "qshort")
/**
 * Représente une question à réponse courte.
 */
public class QShort extends Questioner{

    @OneToOne(mappedBy = "qshort",cascade = CascadeType.ALL)
    private QShortAnswer answer;
}
