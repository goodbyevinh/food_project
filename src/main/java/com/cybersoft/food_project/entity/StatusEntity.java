package com.cybersoft.food_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "status")
public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "status")
    private Set<OrderStatusEntity> orderStatuses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<OrderStatusEntity> getOrderStatuses() {
        return orderStatuses;
    }

    public void setOrderStatuses(Set<OrderStatusEntity> orderStatuses) {
        this.orderStatuses = orderStatuses;
    }
}
