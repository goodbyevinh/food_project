package com.cybersoft.food_project.entity.id;


import javax.persistence.Column;
import java.io.Serializable;

public class OrderStatusId implements Serializable {
    @Column(name = "id_order")
    private int idOrder;
    @Column(name = "id_status")
    private int idStatus;

    public OrderStatusId(int idOrder, int idStatus) {
        this.idOrder = idOrder;
        this.idStatus = idStatus;
    }

    public OrderStatusId() {
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }
}
