package com.cybersoft.food_project.dto;

import com.cybersoft.food_project.model.FoodModel;

import java.util.List;
import java.util.Map;

public class OrderDetailDTO {
    private String address;
    private String restaurantName;
    private Map<String, Integer> foods;
    private float subTotal;
    private float fee;
    private float total;
    private int review;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Map<String, Integer> getFoods() {
        return foods;
    }

    public void setFoods(Map<String, Integer> foods) {
        this.foods = foods;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }
}
