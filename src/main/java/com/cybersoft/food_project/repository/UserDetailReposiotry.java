package com.cybersoft.food_project.repository;

import com.cybersoft.food_project.entity.UserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailReposiotry extends JpaRepository<UserDetailEntity, Integer> {
}
