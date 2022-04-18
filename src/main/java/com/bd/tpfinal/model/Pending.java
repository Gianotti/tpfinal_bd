package com.bd.tpfinal.model;

import com.bd.tpfinal.utils.DeliveryException;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class Pending extends OrderStatus {

    public Pending() {}

    public Pending(Order order) {
        super(order, "Pending");
    }

    public Pending(OrderStatus orderStatus) {
        super(orderStatus.getOrder(), orderStatus.getName(), orderStatus.getStartDate(), orderStatus.getCancelledByClient());
    }
    public Pending(Order order, Date startDate) {super(order, "Pending", startDate); }

    @Override
    public boolean canAssign() {
        return true;
    }

    @Override
    public boolean canAddItem() {
        return true;
    }

    @Override
    public boolean canCancel() {
        return true;
    }

    @Override
    public void assign(DeliveryMan deliveryMan) throws DeliveryException {
        if (this.canAssign()) {
            deliveryMan.setFree(false);
            this.order.setDeliveryMan(deliveryMan);
            this.order.setOrderStatus(new Assigned(this.order));
        } else {
            throw new DeliveryException("The order can't be assigned to the delivery man");
        }
    }

    @Override
    public void cancel() throws DeliveryException {
        if (this.canCancel()) {
            this.order.setOrderStatus(new Cancelled(this.order));
            this.order.getClient().addScore(0); // Pending no penaliza al cliente. Un pedido esta confirmado cuando esta confirmado cuando se asigna repartirdor (segundo bullet)."Resta un punto cuando cancela uno ya confirmado y asignado".
        } else {
            throw new DeliveryException("The order can't be cancelled");
        }
    }


}
