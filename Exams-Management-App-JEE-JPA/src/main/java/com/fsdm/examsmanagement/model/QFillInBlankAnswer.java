package com.fsdm.examsmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="QFIllINBlanksAnswer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QFillInBlankAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answer;

    @ManyToOne
    @JoinColumn(name = "qfillinblankanswer")
    private QFillInBlank qFillInBlanck;
}
