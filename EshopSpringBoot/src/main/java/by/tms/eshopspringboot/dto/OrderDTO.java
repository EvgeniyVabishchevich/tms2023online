package by.tms.eshopspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private LocalDate date;
    private Long id;
    private Map<ProductDTO, Integer> products;
    private Long userId;

    public OrderDTO(LocalDate date, Map<ProductDTO, Integer> products, Long userId) {
        this.date = date;
        this.products = products;
        this.userId = userId;
    }
}
