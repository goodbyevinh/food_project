package com.cybersoft.food_project.entity.id;

import javax.persistence.Column;
import java.io.Serializable;

public class BookmarkRestaurantId implements Serializable {
    @Column(name = "id_user")
    private int idUser;
    @Column(name = "id_restaurant")
    private int idRestaurant;

    public BookmarkRestaurantId(int idUser, int idRestaurant) {
        this.idUser = idUser;
        this.idRestaurant = idRestaurant;
    }

    public BookmarkRestaurantId() {
    }

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
}
