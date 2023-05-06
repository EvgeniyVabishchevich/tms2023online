package by.tms.eshopspringboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Order {
    private LocalDate date;
    private int id;
    private Map<Product, Integer> products;

    public Order(LocalDate date, Map<Product, Integer> products) {
        this.date = date;
        this.products = products;
    }
}
