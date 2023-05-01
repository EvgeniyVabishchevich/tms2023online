package by.tms.eshopspringboot.repository;

import by.tms.eshopspringboot.model.Order;

import java.util.List;

public interface OrderRepository {
    void saveOrder(int userId, Order order);

    List<Order> getOrdersByUserId(int userId);
}
