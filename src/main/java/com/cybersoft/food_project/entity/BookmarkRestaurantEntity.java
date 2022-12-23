package com.cybersoft.food_project.entity;

import com.cybersoft.food_project.entity.id.BookmarkRestaurantId;

import javax.persistence.*;

@Entity(name = "bookmark_restaurant")
@IdClass(BookmarkRestaurantId.class)
public class BookmarkRestaurantEntity {
    @Id
    private int idUser;
    @Id
    private int idRestaurant;

    @ManyToOne
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_restaurant", insertable = false, updatable = false)
    private RestaurantEntity restaurant;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }
}
