package com.cybersoft.food_project.dto;

import com.cybersoft.food_project.model.FoodOrderModel;

import java.util.List;

public class OrderUpcomingDTO {
    private int id;

    private String time;
    private String status;
    private List<FoodOrderModel> foods;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<FoodOrderModel> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodOrderModel> foods) {
        this.foods = foods;
    }
}
