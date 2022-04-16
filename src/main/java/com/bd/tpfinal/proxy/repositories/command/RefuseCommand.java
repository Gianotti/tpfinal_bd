package com.bd.tpfinal.proxy.repositories.command;

import com.bd.tpfinal.dtos.common.ChangeOrderStatusDto;
import com.bd.tpfinal.dtos.common.OrderDto;
import com.bd.tpfinal.exceptions.persistence.PersistenceEntityException;
import com.bd.tpfinal.mappers.orders.OrderMapper;
import com.bd.tpfinal.model.Cancel;
import com.bd.tpfinal.model.Order;
import com.bd.tpfinal.model.Qualification;
import com.bd.tpfinal.repositories.DeliveryManRepository;
import com.bd.tpfinal.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.Date;

public class RefuseCommand extends ChangeStatusCommand{

    public RefuseCommand(OrderRepository orderRepository, DeliveryManRepository deliveryManRepository, OrderMapper orderMapper) {
        super(orderRepository, deliveryManRepository, orderMapper);
    }

    @Override
    @Transactional
    public OrderDto execute(ChangeOrderStatusDto request) throws PersistenceEntityException {
        Order order = getOrder(request);

        Cancel cancel = new Cancel();
        cancel.setCancelledByClient(false);
        cancel.setStartDate(new Date());
        cancel.setOrder(order);
        order.setStatus(cancel);

        Qualification qualification = new Qualification();
        qualification.setOrder(order);
        qualification.setScore(-2);
        qualification.setCommentary("Order refused by delivery man.");

        order.setQualification(qualification);
        order.getDeliveryMan().getOrdersPending().remove(order);

        order = orderRepository.save(order);

        return orderMapper.toOrderDto(order);
    }
}
