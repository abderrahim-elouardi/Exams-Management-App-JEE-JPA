package com.fsdm.examsmanagement.dao;

import com.fsdm.examsmanagement.model.QAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface for QAnswer entities.
public interface QAnswerRepository extends JpaRepository<QAnswer, Long> {
}
