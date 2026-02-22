package com.fsdm.examsmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "qshort")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QShort extends Questioner{
    @OneToMany(mappedBy = "qshort")
    private List<QAnswer> answerList;
}
