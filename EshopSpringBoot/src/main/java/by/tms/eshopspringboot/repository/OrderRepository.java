package by.tms.eshopspringboot.repository;

import by.tms.eshopspringboot.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);
}
