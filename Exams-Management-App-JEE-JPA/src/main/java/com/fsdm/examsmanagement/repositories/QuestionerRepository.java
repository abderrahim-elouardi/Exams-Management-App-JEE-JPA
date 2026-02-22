package com.fsdm.examsmanagement.repositories;

import com.fsdm.examsmanagement.model.Questioner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionerRepository extends JpaRepository<Questioner, Long>{
}
