package com.example.firsttask.dao.repository;

import com.example.firsttask.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {
    Optional<UserEntity> findUserByEmail(String email);
    UserEntity findByFirstName(String firstName);
}