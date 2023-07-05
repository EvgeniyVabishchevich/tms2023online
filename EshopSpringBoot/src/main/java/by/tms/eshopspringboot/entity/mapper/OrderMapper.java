package by.tms.eshopspringboot.entity.mapper;

import by.tms.eshopspringboot.dto.OrderDTO;
import by.tms.eshopspringboot.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO orderToOrderDTO(Order order);

    Order OrderDTOtoOrder(OrderDTO orderDTO);
}
