package com.cybersoft.food_project.entity.id;

import javax.persistence.Column;
import java.io.Serializable;

public class BookmarkFoodId implements Serializable {
    @Column(name = "id_user")
    private int idUser;
    @Column(name = "id_food")
    private int idFood;

    public BookmarkFoodId(int idUser, int idFood) {
        this.idUser = idUser;
        this.idFood = idFood;
    }

    public BookmarkFoodId() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }
}
