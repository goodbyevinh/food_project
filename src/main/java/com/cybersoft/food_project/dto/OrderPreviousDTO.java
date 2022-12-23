package com.cybersoft.food_project.dto;

import com.cybersoft.food_project.model.FoodModel;
import com.cybersoft.food_project.model.FoodOrderModel;

import java.util.List;

public class OrderPreviousDTO {
    private int id;
    private String restaurant;
    private String datetime;
    private List<FoodOrderModel> foods;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public List<FoodOrderModel> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodOrderModel> foods) {
        this.foods = foods;
    }
}
