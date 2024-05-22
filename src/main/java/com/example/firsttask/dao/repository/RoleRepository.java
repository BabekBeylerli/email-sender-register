package com.example.firsttask.dao.repository;

import com.example.firsttask.dao.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
}

