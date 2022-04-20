package com.bd.tpfinal.services;

import com.bd.tpfinal.model.Order;
import com.bd.tpfinal.model.OrderStatus;
import com.bd.tpfinal.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService
{
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository)
    {
        this.orderRepository = orderRepository;
    }

    @Override
    public void addOrder(Order newOrder)
    {
        //creo un status PENDING
        //Creo la orden y le asigno el status anterior
        //OrderStatus orderStatus = Status_Factory.getInstance(Order_Status_Enum.PENDING,newOrder,"pending",newOrder.getDateOfOrder());
        // this.orderStatusRepository.save(orderStatus);
        // newOrder.setStatus(orderStatus);
        //this.orderStatusRepository.save(newOrder.getStatus());
        this.orderRepository.save(newOrder);
    }

    @Override
    public List<Order> getAll()
    {
        return this.orderRepository.findAll();
    }

    /**
     * retorna la orden con el atributo status reconstruido.
     *
     * @param id
     * @return
     */
    @Override
    public Optional<Order> getById(Long id)
    {
        Order order_aux;
        Optional<Order> order_aux_optional = this.orderRepository.findById(id);
        order_aux = order_aux_optional.get();
        //OrderStatus orderStatus_aux = this.orderStatusRepository.findByOrder(id);
        //order_aux.setOrderStatus(orderStatus_aux);
        return Optional.of(order_aux);
    }

    @Override
    public Order getByNumber(int number)
    {
        Order order_aux = this.orderRepository.findByNumber(number);
        //OrderStatus orderStatus_aux = this.orderStatusRepository.findByOrder(order_aux.getId());
        //order_aux.setOrderStatus(orderStatus_aux);
        order_aux.setStatusByName();
        return order_aux;
    }
}
