package com.fsdm.examsmanagement.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="qcm")
@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Represents a multiple-choice question with a list of possible answers.
 */
public class QCM extends Questioner {
    /**
     * List of possible answers for this multiple-choice question.
     */
    @OneToMany(mappedBy = "qcm",cascade= CascadeType.ALL)
    private List<QCMAnswer> answerList;
}
