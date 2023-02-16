package com.cybersoft.food_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "t_order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "estimate_ship")
    private String estimateShip;
    @Column(name = "deliver_address")
    private String deliverAddress;
    @Column(name = "fee")
    private float fee;
    @Column(name = "cart")
    private boolean cart;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @OneToMany(mappedBy = "order")
    private Set<OrderStatusEntity> orderStatuses;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FoodOrderEntity> foodOrders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstimateShip() {
        return estimateShip;
    }

    public void setEstimateShip(String estimateShip) {
        this.estimateShip = estimateShip;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public String getDeliverAddress() {
        return deliverAddress;
    }

    public void setDeliverAddress(String deliverAddress) {
        this.deliverAddress = deliverAddress;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Set<OrderStatusEntity> getOrderStatuses() {
        return orderStatuses;
    }

    public void setOrderStatuses(Set<OrderStatusEntity> orderStatuses) {
        this.orderStatuses = orderStatuses;
    }

    public Set<FoodOrderEntity> getFoodOrders() {
        return foodOrders;
    }

    public void setFoodOrders(Set<FoodOrderEntity> foodOrders) {
        this.foodOrders = foodOrders;
    }

    public boolean isCart() {
        return cart;
    }

    public void setCart(boolean cart) {
        this.cart = cart;
    }
}
