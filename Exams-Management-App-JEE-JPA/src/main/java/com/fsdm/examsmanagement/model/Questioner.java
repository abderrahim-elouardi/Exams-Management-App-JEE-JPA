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
public abstract class Questioner {
    @Id
    @Column(name = "questionerid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "point")
    private int point;

    @Column(name = "question")
    private String question;

    @Column(name = "responsetime")
    private Date responseTime;
}
