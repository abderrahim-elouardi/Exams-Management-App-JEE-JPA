package com.fsdm.examsmanagement.repositories;

import com.fsdm.examsmanagement.model.Questioner;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface for Questioner entities.
public interface QuestionerRepository extends JpaRepository<Questioner, Long>{
}
