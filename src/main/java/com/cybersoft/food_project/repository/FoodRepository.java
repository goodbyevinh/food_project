package com.cybersoft.food_project.repository;

import com.cybersoft.food_project.entity.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Integer> {
    List<FoodEntity> findFoodEntitiesByNameContains(String name);

    @Query(value = "select f from food as f join bookmark_food as bf on f.id = bf.idFood where bf.user.email = ?1")
    List<FoodEntity> findFoodBookmarkByUser(String email);
    @Query(value = "select f.* from food as f join category as c on f.id_category = c.id where c.id = ?1 order by f.id asc limit 6", nativeQuery = true)
    List<FoodEntity> findFoodEntitiesByCategoryLimit6(int id);
    @Query(value = "select f from food as f where f.category.id = ?1")
    List<FoodEntity> findFoodEntitiesByCategory_Id(int id);
    List<FoodEntity> findFoodEntitiesByRestaurant_Id(int id);
    FoodEntity findFoodEntityById(int id);
}
