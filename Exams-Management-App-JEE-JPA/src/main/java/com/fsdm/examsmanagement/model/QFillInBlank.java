package com.fsdm.examsmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "qfillinblank")
/**
 * Représente une question à trou.
 */
public class QFillInBlank extends Questioner{
}
