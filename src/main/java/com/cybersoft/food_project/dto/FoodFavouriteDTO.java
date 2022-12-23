package com.cybersoft.food_project.dto;

public class FoodFavouriteDTO {
    private String title;
    private String image;
    private float avgRate;
    private int review;
    private String category;

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
