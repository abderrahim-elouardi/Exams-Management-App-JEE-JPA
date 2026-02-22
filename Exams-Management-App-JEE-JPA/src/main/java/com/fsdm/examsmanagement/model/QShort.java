package com.fsdm.examsmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "qshort")
/**
 * Represents a short-answer question.
 */
public class QShort extends Questioner{
}
