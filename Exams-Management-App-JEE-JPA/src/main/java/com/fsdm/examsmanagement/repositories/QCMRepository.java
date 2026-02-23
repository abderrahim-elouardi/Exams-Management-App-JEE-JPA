package com.fsdm.examsmanagement.repositories;

import com.fsdm.examsmanagement.model.QCM;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface for QCM entities.
public interface QCMRepository extends JpaRepository<QCM, Long> {
}
