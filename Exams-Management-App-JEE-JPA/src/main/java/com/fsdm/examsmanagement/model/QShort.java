package com.fsdm.examsmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "qshort")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
/**
 * Représente une question à réponse courte.
 */
public class QShort extends Questioner{

    @OneToOne(mappedBy = "qshort",cascade = CascadeType.ALL)
    private QShortAnswer answer;
}
