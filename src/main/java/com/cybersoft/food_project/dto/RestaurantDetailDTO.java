package com.cybersoft.food_project.dto;

import com.cybersoft.food_project.entity.FoodEntity;
import com.cybersoft.food_project.model.FoodModel;

import java.util.List;
import java.util.Set;

public class RestaurantDetailDTO {
    private String title = "";
    private String image = "";
    private float avgRate = 0;
    private String desc = "";
    private int review;
    private String category;
    private List<FoodModel> foods;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(float avgRate) {
        this.avgRate = avgRate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<FoodModel> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodModel> foods) {
        this.foods = foods;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
