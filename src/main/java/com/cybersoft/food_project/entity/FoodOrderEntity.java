package com.cybersoft.food_project.entity;

import com.cybersoft.food_project.entity.id.FoodOrderId;

import javax.persistence.*;

@Entity(name = "food_order")
@IdClass(FoodOrderId.class)
public class FoodOrderEntity {
    @Id
    private int idFood;
    @Id
    private int idOrder;
    @Column(name = "price")
    private float price;
    @Column(name = "quantity")
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "id_food" , insertable = false, updatable = false)
    private FoodEntity food;
    @ManyToOne
    @JoinColumn(name = "id_order", insertable = false, updatable = false)
    private OrderEntity order;

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public FoodEntity getFood() {
        return food;
    }

    public void setFood(FoodEntity food) {
        this.food = food;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

}
