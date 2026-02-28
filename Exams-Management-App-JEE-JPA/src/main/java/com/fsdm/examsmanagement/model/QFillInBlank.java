package com.fsdm.examsmanagement.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "qfillinblank")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Représente une question à trou.
 */
public class QFillInBlank extends Questioner{

    @OneToMany(mappedBy = "qFillInBlanck",cascade = CascadeType.ALL)
    private List<QFillInBlankAnswer> qFillInBlankAnswer;
}
