package by.tms.eshopspringboot.service;

import by.tms.eshopspringboot.dto.OrderDTO;

import java.util.List;

public interface OrderServiceAware {

    List<OrderDTO> getOrdersByUserId(Long userId);

    void addOrder(OrderDTO order);
}
