package com.cybersoft.food_project.controller;


import com.cybersoft.food_project.dto.FoodFavouriteDTO;
import com.cybersoft.food_project.dto.RestaurantDTO;
import com.cybersoft.food_project.dto.RestaurantDetailDTO;
import com.cybersoft.food_project.dto.RestaurantFavouriteDTO;
import com.cybersoft.food_project.entity.RestaurantEntity;
import com.cybersoft.food_project.payload.response.DataResponse;
import com.cybersoft.food_project.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @GetMapping("")
    public ResponseEntity<?> getRestaurants() {
        List<RestaurantDTO> restaurants = restaurantService.getRestaurants();
        DataResponse dataResponse = new DataResponse();
        dataResponse.setDesc("");
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(true);
        dataResponse.setData(restaurants);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @GetMapping("/limit")
    public ResponseEntity<?> getRestaurantsLimit() {
        List<RestaurantDTO> restaurants = restaurantService.getRestaurantsLimit();
        DataResponse dataResponse = new DataResponse();
        dataResponse.setDesc("");
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(true);
        dataResponse.setData(restaurants);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getDetailRestaurant(@PathVariable("id") int id) {
        RestaurantDetailDTO detailDTO = restaurantService.getRestaurantDetail(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setDesc("");
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(true);
        dataResponse.setData(detailDTO);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @GetMapping("/clear-cache")
    public ResponseEntity<?> clearCacheRestaurant() {
        restaurantService.clearAllCache();
        return new ResponseEntity<>("", HttpStatus.OK);
    }
    @GetMapping("/favourite")
    ResponseEntity<DataResponse> getRestaurantFavourites() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = (String) securityContext.getAuthentication().getPrincipal();
        List<RestaurantFavouriteDTO> restaurants = restaurantService.getFavouriteRestaurants(username);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setDesc("");
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(true);
        dataResponse.setData(restaurants);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

}
