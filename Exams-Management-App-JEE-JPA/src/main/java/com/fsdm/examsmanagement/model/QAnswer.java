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
public class QAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qanswerid")
    private Long id;

    @Column(name = "answer")
    private String answer;

    @Column(name = "status")
    private int status;

    @ManyToOne
    @JoinColumn(name = "qcmid")
    private QCM qcm;

    @ManyToOne
    @JoinColumn(name = "qshortid")
    private QShort qShort;

}
