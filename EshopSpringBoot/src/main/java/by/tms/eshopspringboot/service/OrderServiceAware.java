package by.tms.eshopspringboot.service;

import by.tms.eshopspringboot.entity.Order;

import java.util.List;

public interface OrderServiceAware {

    List<Order> getOrdersByUserId(int userId);

    void addOrder(Order order);
}
