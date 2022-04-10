package com.bd.tpfinal.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@DiscriminatorValue("DELIVERY_MAN")
public class DeliveryMan extends User{

    private int numberOfSuccessOrders;

    private boolean free;
    @Temporal(TemporalType.DATE)
    private Date dateOfAdmission;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Order> ordersPending;

    public int getNumberOfSuccessOrders() {
        return numberOfSuccessOrders;
    }

    public void setNumberOfSuccessOrders(int numberOfSuccessOrders) {
        this.numberOfSuccessOrders = numberOfSuccessOrders;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Date getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(Date dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public List<Order> getOrdersPending() {
        return ordersPending;
    }

    public void setOrdersPending(List<Order> ordersPending) {
        this.ordersPending = ordersPending;
    }
}
