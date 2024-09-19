package com.taskmanagement.userTask.repository;

import com.taskmanagement.userTask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
