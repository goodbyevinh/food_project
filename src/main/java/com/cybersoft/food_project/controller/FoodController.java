package com.cybersoft.food_project.controller;


import com.cybersoft.food_project.dto.FoodDTO;
import com.cybersoft.food_project.dto.FoodDetailDTO;
import com.cybersoft.food_project.dto.FoodFavouriteDTO;
import com.cybersoft.food_project.payload.response.DataResponse;
import com.cybersoft.food_project.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("food")
public class FoodController {

    @Autowired
    FoodService foodService;

    @GetMapping("/{id}")
    ResponseEntity<DataResponse> getFoodsByRestaurant(@PathVariable("id") int id) {
        List<FoodDTO> foods = foodService.getFoodsByRestaurant(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setDesc("");
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(true);
        dataResponse.setData(foods);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getFoodDetail(@PathVariable("id") int id) {
        FoodDetailDTO food = foodService.getFoodDetailById(id);
        DataResponse dataResponse = new DataResponse();
        if (food == null) {
            dataResponse.setSuccess(true);
            dataResponse.setStatus(200);
            dataResponse.setData("");
            dataResponse.setDesc("Not find food detail");
        } else {
            dataResponse.setSuccess(true);
            dataResponse.setStatus(200);
            dataResponse.setData(food);
            dataResponse.setDesc("");
        }
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @PostMapping("/asianfoodlimit")
    ResponseEntity<DataResponse> getAsianFoodLimit6(@RequestParam("id") int id) {
        System.out.println("kiemtra" + id);
        List<FoodDTO> foods = foodService.getFoodsByCategoryLimit6(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setDesc("");
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(true);
        dataResponse.setData(foods);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @PostMapping("/asianfood")
    ResponseEntity<DataResponse> getAsianFoodAll(@RequestParam("id") int id) {
        List<FoodDTO> foods = foodService.getFoodsByCategory(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setDesc("");
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(true);
        dataResponse.setData(foods);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @GetMapping("/favourite")
    ResponseEntity<DataResponse> getFoodFavourites() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = (String) securityContext.getAuthentication().getPrincipal();
        List<FoodFavouriteDTO> foods = foodService.getFoodsFavouritesByUser(username);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setDesc("");
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(true);
        dataResponse.setData(foods);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @PostMapping("/bookmark")
    ResponseEntity<?> bookmarkFood(@RequestParam("id") int id) {
        DataResponse dataResponse = new DataResponse();


        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @PostMapping("/cart")
    ResponseEntity<DataResponse> getCart(@RequestBody Set<Integer> foods) {
        Map<Integer,Map<String, Integer>> map = foodService.getCart(foods);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setDesc("");
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(true);
        dataResponse.setData(map);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

}
