package com.cybersoft.food_project.dto;

import com.cybersoft.food_project.model.FoodModel;
import com.cybersoft.food_project.model.RestaurantModel;

import java.util.List;

public class OrderCartDTO {

    private List<FoodModel> foods;
    private float shipFee;
    private float checkout;

    public List<FoodModel> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodModel> foods) {
        this.foods = foods;
    }

    public float getShipFee() {
        return shipFee;
    }

    public void setShipFee(float shipFee) {
        this.shipFee = shipFee;
    }

    public float getCheckout() {
        return checkout;
    }

    public void setCheckout(float checkout) {
        this.checkout = checkout;
    }
}
