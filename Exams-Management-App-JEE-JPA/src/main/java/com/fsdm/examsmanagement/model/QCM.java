package com.fsdm.examsmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="qcm")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QCM extends Questioner {
    @OneToMany(mappedBy = "qcm")
    private List<QAnswer> answerList;
}
