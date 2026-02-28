package com.fsdm.examsmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="qanswer")
@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Représente une réponse possible pour une question à choix multiple.
 */
public class QCMAnswer {
    /**
     * Identifiant unique de la réponse.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qanswerid")
    private Long id;

    /**
     * Texte de la réponse.
     */
    @Column(name = "answer")
    private String answer;

    /**
     * Statut de la réponse (par exemple correcte ou incorrecte).
     */
    @Column(name = "status")
    private int status;

    /**
     * Question à choix multiple liée à cette réponse.
     */
    @ManyToOne
    @JoinColumn(name = "qcmid")
    private QCM qcm;


    @Override
    public String toString() {
        return "QCMAnswer{" +
                "id=" + id +
                ", answer='" + answer + '\'' +
                ", status=" + status +
                ", qcm=" + qcm +
                '}';
    }
}
