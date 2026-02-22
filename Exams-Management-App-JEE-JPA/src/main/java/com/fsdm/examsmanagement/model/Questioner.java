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
 * Base class for all question types in the exam system.
 */
public abstract class Questioner {
    /**
     * Unique identifier of the question.
     */
    @Id
    @Column(name = "questionerid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Number of points given for this question.
     */
    @Column(name = "point")
    private int point;

    /**
     * Text of the question shown to the student.
     */
    @Column(name = "question")
    private String question;

    /**
     * Allowed time to answer this question.
     */
    @Column(name = "responsetime")
    private Date responseTime;
}
