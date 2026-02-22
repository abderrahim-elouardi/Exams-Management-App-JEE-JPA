package com.fsdm.examsmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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

    @ManyToMany(mappedBy = "studentList")
    private List<Exam> examList;

}
