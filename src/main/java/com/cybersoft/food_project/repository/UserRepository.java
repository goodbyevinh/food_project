package com.cybersoft.food_project.repository;

import com.cybersoft.food_project.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findByEmailAndPassword(String email, String password);
}
