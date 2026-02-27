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
 * Représente une question à choix multiple avec une liste de réponses possibles.
 */
public class QCM extends Questioner {
    /**
     * Liste des réponses possibles pour cette question à choix multiple.
     */
    @OneToMany(mappedBy = "qcm",cascade= CascadeType.ALL)
    private List<QCMAnswer> answerList;
}
