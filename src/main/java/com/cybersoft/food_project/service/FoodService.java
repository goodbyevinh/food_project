package com.cybersoft.food_project.service;

import com.cybersoft.food_project.dto.FoodDTO;
import com.cybersoft.food_project.dto.FoodDetailDTO;
import com.cybersoft.food_project.dto.FoodFavouriteDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface FoodService {
    FoodDetailDTO getFoodDetailById(int id);
    List<FoodDTO> getFoodsByName(String name);
    List<FoodDTO> getFoodsByCategoryLimit6(int categoryId);
    List<FoodDTO> getFoodsByCategory(int categoryId);
    List<FoodDTO> getFoodsByRestaurant(int id);
    List<FoodFavouriteDTO> getFoodsFavouritesByUser(String email);
    Map<Integer,Map<String, Integer>> getCart(Set<Integer> foods);
}
