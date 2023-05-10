package by.tms.eshopspringboot.repository.impl;

import by.tms.eshopspringboot.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepositoryImpl extends CrudRepository<Order, Integer> {
    List<Order> findAllByUserId(int userId);
}
