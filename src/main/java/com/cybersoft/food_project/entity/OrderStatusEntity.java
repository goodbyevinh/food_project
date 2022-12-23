package com.cybersoft.food_project.entity;

import com.cybersoft.food_project.entity.id.OrderStatusId;

import javax.persistence.*;

@Entity(name = "order_status")
@IdClass(OrderStatusId.class)
public class OrderStatusEntity {
    @Id
    private int idOrder;
    @Id
    private int idStatus;
    @Column(name = "current")
    private boolean current;

    @ManyToOne
    @JoinColumn(name = "id_order", insertable = false, updatable = false)
    private OrderEntity order;
    @ManyToOne
    @JoinColumn(name = "id_status",insertable = false, updatable = false)
    private StatusEntity status;

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

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }
}
