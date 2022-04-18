package com.bd.tpfinal.services;
import com.bd.tpfinal.model.*;
import com.bd.tpfinal.utils.DeliveryException;

import java.util.List;


public interface DeliveryService {

    public Client newClient(Client client);

    public Client editClient(String username, Client client) throws DeliveryException;

    public void desactiveClient(String username);

    public Client getClientInfo(String username);

//    public List<Order> getClientOrders(String username);

    public DeliveryMan newDeliveryMan(DeliveryMan deliveryMan);

    public DeliveryMan editDeliveryMan(String username, DeliveryMan deliveryMan) throws DeliveryException;

    public Product editProduct(Long number, Product product)  throws DeliveryException;

    public void desactiveDeliveryMan(String username);

    public DeliveryMan getDeliveryManInfo(String username);

    public Order newOrderPending(Order order);

    public Order getOrderinfo(long number);

    public DeliveryMan confirmOrder(long number) throws DeliveryException;

    public void deliverOrder(long number) throws DeliveryException;

    public void refuseOrder(long number) throws DeliveryException;

    public void cancelOrder(long number) throws DeliveryException;

    public void finishOrder(long number) throws DeliveryException;

    public void qualifyOrder(long number, Qualification qualification) throws DeliveryException;

    public Address getAddress(long id);

    public Address createAddress(Address newAddress);

    public SupplierType getSupplierType(long id);

    public SupplierType createSupplierType(SupplierType newsSupplierType);

    public Supplier getSupplier(long id);

    public Supplier createSupplier(Supplier newsSupplier);

    public Item createItem(Item newsItem);
    
    Object deleteItem(Item item);

    public void deleteProduct(Long id) throws DeliveryException;

    List<Item> getItemsByOrderNumber(Long number);

    public Item getItemWithID(long id);

    public List<Product> getProductBySupplier(long id);

}

