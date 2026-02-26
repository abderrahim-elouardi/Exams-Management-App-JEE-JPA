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
 * Represents one possible answer for a multiple-choice question.
 */
public class QCMAnswer {
    /**
     * Unique identifier of the answer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qanswerid")
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
    @ManyToOne
    @JoinColumn(name = "qcmid")
    private QCM qcm;

}
