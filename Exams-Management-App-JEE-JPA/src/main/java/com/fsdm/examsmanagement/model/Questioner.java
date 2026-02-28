package com.fsdm.examsmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Entity
@Table(name="questioner")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Classe de base pour tous les types de questions dans le système d'examen.
 */
public abstract class Questioner {
    /**
     * Identifiant unique de la question.
     */
    @Id
    @Column(name = "questionerid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de points attribués à cette question.
     */
    @Column(name = "point")
    private int point;

    /**
     * Texte de la question affichée à l'étudiant.
     */
    @Column(name = "question")
    private String question;

    /**
     * Examen auquel cette question appartient.
     */
    @ManyToOne
    @JoinColumn(name = "examenid")
    private Exam exam;

    @Override
    public String toString() {
        return "Questioner{" +
                "id=" + id +
                ", point=" + point +
                ", question='" + question + '\'' +
                ", exam=" + exam +
                '}';
    }
}