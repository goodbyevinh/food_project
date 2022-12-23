package com.cybersoft.food_project.entity;

import com.cybersoft.food_project.entity.id.BookmarkFoodId;
import com.cybersoft.food_project.entity.id.BookmarkRestaurantId;

import javax.persistence.*;

@Entity(name = "bookmark_food")
@IdClass(BookmarkFoodId.class)
public class BookmarkFoodEntity {
    @Id
    private int idUser;
    @Id
    private int idFood;

    @ManyToOne
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_food", insertable = false, updatable = false)
    private FoodEntity food;

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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public FoodEntity getFood() {
        return food;
    }

    public void setFood(FoodEntity food) {
        this.food = food;
    }
}
