package com.cybersoft.food_project.repository;

import com.cybersoft.food_project.entity.FoodAddonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodAddonRepository extends JpaRepository<FoodAddonEntity, Integer> {
}
