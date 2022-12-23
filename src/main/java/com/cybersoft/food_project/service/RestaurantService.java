package com.cybersoft.food_project.service;

import com.cybersoft.food_project.dto.RestaurantDTO;
import com.cybersoft.food_project.dto.RestaurantDetailDTO;
import com.cybersoft.food_project.dto.RestaurantFavouriteDTO;
import com.cybersoft.food_project.entity.RestaurantEntity;

import java.util.List;

public interface RestaurantService {
    List<RestaurantDTO> getRestaurants();
    List<RestaurantDTO> getRestaurantsLimit();
    List<RestaurantDTO> getRestaurantsByName(String name);
    List<RestaurantFavouriteDTO> getFavouriteRestaurants(String email);
    RestaurantDetailDTO getRestaurantDetail(int id);
    void clearAllCache();
}
