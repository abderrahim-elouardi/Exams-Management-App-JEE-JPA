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
@Table(name="Student")
public class Student extends User{
    private String cne;

    @ManyToMany(fetch = FetchType.EAGER) // On ajoute le mode EAGER ici
    @JoinTable(
            name = "STUDENT_EXAM",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id")
    )
    private List<Exam> examList;

    @ManyToMany(fetch = FetchType.EAGER) // On ajoute le mode EAGER ici
    @JoinTable(
            name = "STUDENT_PASSED_EXAM",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "passed_exam_id")
    )
    private List<Exam> passedExamList;


    /// /
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamNote> questions;

    @Override
    public String toString() {
        return "Student{" +
                "cne='" + cne + '\'' +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
