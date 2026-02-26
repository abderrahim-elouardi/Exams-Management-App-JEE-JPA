package com.fsdm.examsmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="QShortAnswer")
@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Represents one possible answer for a multiple-choice question.
 */
public class QShortAnswer {
    /**
     * Unique identifier of the answer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QShortId")
    private Long id;

    /**
     * Text content of the answer.
     */
    @Column(name = "answer")
    private String answer;

    /**
     * Status of the answer (for example correct or incorrect).
     */
    @Column(name = "status")
    private int status;

    /**
     * Multiple-choice question that owns this answer.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "qshortid")
    private QShort qshort;

}
