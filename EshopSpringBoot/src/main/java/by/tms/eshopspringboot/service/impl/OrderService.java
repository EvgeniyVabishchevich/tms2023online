package by.tms.eshopspringboot.service.impl;

import by.tms.eshopspringboot.entity.Order;
import by.tms.eshopspringboot.repository.impl.OrderRepositoryImpl;
import by.tms.eshopspringboot.service.OrderServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServiceAware {
    private final OrderRepositoryImpl orderRepositoryImpl;

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return orderRepositoryImpl.findAllByUserId(userId);
    }

    @Override
    public void addOrder(Order order) {
        orderRepositoryImpl.save(order);
    }
}
