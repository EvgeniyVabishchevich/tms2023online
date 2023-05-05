package by.tms.eshopspringboot.service.impl;

import by.tms.eshopspringboot.model.Order;
import by.tms.eshopspringboot.repository.OrderRepository;
import by.tms.eshopspringboot.service.OrderServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServiceAware {
    private final OrderRepository orderDao;

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return orderDao.getOrdersByUserId(userId);
    }

    @Override
    public void addOrder(int userId, Order order) {
        orderDao.saveOrder(userId, order);
    }
}
