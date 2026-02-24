package com.fsdm.examsmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EXAM")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExam;

    private String titre;
    private LocalDateTime deadline;

    @ManyToOne
    @JoinColumn(name = "idAdmin")
    private Administrator admin;

    @ManyToMany(mappedBy = "examList")
    private List<Student> studentList;

//    @OneToMany(mappedBy = "examen")
//    private List<Questioner> questions;

    @OneToMany(mappedBy = "exam")
    private List<Questioner> questioner;
}
