package com.cybersoft.food_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "food_detail")
public class FoodDetailEntity {
    @Id
    @Column(name = "id_food")
    private int idFood;
    @Column(name = "description")
    private String description;
    @Column(name = "create_date")
    private String createDate;

    @OneToOne
    @JoinColumn(name = "id_food")
    private FoodEntity food;

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }



    public FoodEntity getFood() {
        return food;
    }

    public void setFood(FoodEntity food) {
        this.food = food;
    }
}
