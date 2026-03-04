package com.fsdm.examsmanagement.model;

import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "qFillInBlanck",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<QFillInBlankAnswer> qFillInBlankAnswer;
}
