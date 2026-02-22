package com.fsdm.examsmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
@Table(name="Administrator")
public class Administrator extends User{

    private String cin;

    @OneToMany(mappedBy = "admin")
    private List<Exam> examList;

}
