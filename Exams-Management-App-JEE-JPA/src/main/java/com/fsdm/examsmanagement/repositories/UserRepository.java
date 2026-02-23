package com.fsdm.examsmanagement.repositories;

import com.fsdm.examsmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
