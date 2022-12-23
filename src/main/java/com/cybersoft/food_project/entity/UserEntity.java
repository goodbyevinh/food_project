package com.cybersoft.food_project.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "token")
    private String token;
    @Column(name = "type_token")
    private String typeToken;
    @Column(name = "verify_code")
    private String verifyCode;
    @Column(name = "verify_code_expired")
    private String verifyCodeExpired;
    @Column(name = "is_active")
    private boolean isActive;

    @OneToOne(mappedBy = "user" ,cascade = CascadeType.ALL)
    private UserDetailEntity userDetail ;
    @OneToMany(mappedBy = "user")
    private Set<FoodReviewEntity> foodReviews;
    @OneToMany(mappedBy = "user")
    private Set<OrderEntity> orders;

    @OneToMany(mappedBy = "user",  cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookmarkRestaurantEntity> bookmarkRestaurants;
    //fetch = FetchType.EAGER load parent cũng sẽ load child
    //fetch = FetchType.LAZY load parent sẽ ko load child
    //orphanRemoval = true . nếu xóa phần tử trong collection thì sẽ xóa trong database
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookmarkFoodEntity> bookmarkFoods;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTypeToken() {
        return typeToken;
    }

    public void setTypeToken(String typeToken) {
        this.typeToken = typeToken;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getVerifyCodeExpired() {
        return verifyCodeExpired;
    }

    public void setVerifyCodeExpired(String verifyCodeExpired) {
        this.verifyCodeExpired = verifyCodeExpired;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public UserDetailEntity getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetailEntity userDetail) {
        this.userDetail = userDetail;
    }

    public Set<FoodReviewEntity> getFoodReviews() {
        return foodReviews;
    }

    public void setFoodReviews(Set<FoodReviewEntity> foodReviews) {
        this.foodReviews = foodReviews;
    }

    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
    }

    public Set<BookmarkRestaurantEntity> getBookmarkRestaurants() {
        return bookmarkRestaurants;
    }

    public void setBookmarkRestaurants(Set<BookmarkRestaurantEntity> bookmarkRestaurants) {
        this.bookmarkRestaurants = bookmarkRestaurants;
    }

    public Set<BookmarkFoodEntity> getBookmarkFoods() {
        return bookmarkFoods;
    }

    public void setBookmarkFoods(Set<BookmarkFoodEntity> bookmarkFoods) {
        this.bookmarkFoods = bookmarkFoods;
    }
}
