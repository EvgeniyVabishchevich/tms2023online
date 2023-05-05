package by.tms.eshopspringboot.service;

import by.tms.eshopspringboot.model.Order;

import java.util.List;

public interface OrderServiceAware {

    List<Order> getOrdersByUserId(int userId);

    void addOrder(int userId, Order order);
}
