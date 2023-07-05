package by.tms.eshopspringboot.service.impl;

import by.tms.eshopspringboot.dto.OrderDTO;
import by.tms.eshopspringboot.entity.Order;
import by.tms.eshopspringboot.entity.mapper.OrderMapper;
import by.tms.eshopspringboot.repository.OrderRepository;
import by.tms.eshopspringboot.service.OrderServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServiceAware {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDTO> getOrdersByUserId(Long userId) {
        List<Order> orderList = orderRepository.findAllByUserId(userId);
        return orderList.stream()
                .map(orderMapper::orderToOrderDTO)
                .toList();
    }

    @Override
    public void addOrder(OrderDTO order) {
        orderRepository.save(orderMapper.OrderDTOtoOrder(order));
    }
}
