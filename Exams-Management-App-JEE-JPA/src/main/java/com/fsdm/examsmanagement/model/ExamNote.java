package com.fsdm.examsmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="EXAMSTUDENT")
public class ExamNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float note;
    private boolean status;

    @ManyToOne
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
