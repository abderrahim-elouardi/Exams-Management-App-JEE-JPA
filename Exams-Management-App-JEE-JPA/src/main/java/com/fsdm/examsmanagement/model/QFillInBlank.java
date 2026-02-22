package com.fsdm.examsmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "qfillinblank")
/**
 * Represents a fill-in-the-blank question.
 */
public class QFillInBlank extends Questioner{
}
