package com.cybersoft.food_project.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "restaurant")
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "image")
    private String image;
    @Column(name = "name")
    private String name;
    @Column(name= "description")
    private String description;
    @Column(name = "category")
    private String category;
    @Column(name = "fee")
    private float fee;

    @OneToMany(mappedBy = "restaurant")
    private Set<RestaurantReviewEntity> restaurantReviews;

    @OneToMany(mappedBy = "restaurant")
    private Set<FoodEntity> foods;
    @OneToMany(mappedBy = "restaurant")
    private Set<BookmarkRestaurantEntity> bookmarkRestaurants;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RestaurantReviewEntity> getRestaurantReviews() {
        return restaurantReviews;
    }

    public void setRestaurantReviews(Set<RestaurantReviewEntity> restaurantReviews) {
        this.restaurantReviews = restaurantReviews;
    }

    public Set<FoodEntity> getFoods() {
        return foods;
    }

    public void setFoods(Set<FoodEntity> foods) {
        this.foods = foods;
    }

    public Set<BookmarkRestaurantEntity> getBookmarkRestaurants() {
        return bookmarkRestaurants;
    }

    public void setBookmarkRestaurants(Set<BookmarkRestaurantEntity> bookmarkRestaurants) {
        this.bookmarkRestaurants = bookmarkRestaurants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

}
