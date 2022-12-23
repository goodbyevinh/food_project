package com.cybersoft.food_project.entity.id;

import javax.persistence.Column;
import java.io.Serializable;

public class FoodOrderId implements Serializable {
    @Column(name = "id_order")
    private int idOrder;
    @Column(name = "id_food")
    private int idFood;

    public FoodOrderId(int idOrder, int idFood) {
        this.idOrder = idOrder;
        this.idFood = idFood;
    }

    public FoodOrderId() {
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }
}
