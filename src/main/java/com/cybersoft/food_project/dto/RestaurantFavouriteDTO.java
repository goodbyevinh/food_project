package com.cybersoft.food_project.dto;

public class RestaurantFavouriteDTO {
    private int id;
    private String title;
    private float avgRate;
    private String image;
    private int review;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(float avgRate) {
        this.avgRate = avgRate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }
}
