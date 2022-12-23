package com.cybersoft.food_project.controller;

import com.cybersoft.food_project.dto.FoodDTO;
import com.cybersoft.food_project.dto.RestaurantDTO;
import com.cybersoft.food_project.payload.response.DataResponse;
import com.cybersoft.food_project.service.FoodService;
import com.cybersoft.food_project.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    FoodService foodService;
    @Autowired
    RestaurantService restaurantService;
    @PostMapping("/restaurant")
    public ResponseEntity<DataResponse> getRestaurants(@RequestParam("name")String name) {
        DataResponse dataResponse = new DataResponse();
        List<RestaurantDTO> restaurants = restaurantService.getRestaurantsByName(name);
        dataResponse.setDesc("");
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setData(restaurants);
        dataResponse.setSuccess(true);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @PostMapping("/food")
    public ResponseEntity<DataResponse> getFoods(@RequestParam("name")String name) {
        DataResponse dataResponse = new DataResponse();
        List<FoodDTO> food = foodService.getFoodsByName(name);
        dataResponse.setDesc("");
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setData(food);
        dataResponse.setSuccess(true);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
