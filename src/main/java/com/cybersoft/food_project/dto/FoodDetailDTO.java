package com.cybersoft.food_project.dto;

import com.cybersoft.food_project.model.FoodAddonModel;

import java.util.List;
import java.util.Set;

public class FoodDetailDTO {
    private String title;
    private String description;
    private String image;
    private float price;
    private List<FoodAddonModel> foodAddons;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<FoodAddonModel> getFoodAddons() {
        return foodAddons;
    }

    public void setFoodAddons(List<FoodAddonModel> foodAddons) {
        this.foodAddons = foodAddons;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
