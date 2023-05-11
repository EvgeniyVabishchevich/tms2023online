package by.tms.eshopspringboot.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
@AllArgsConstructor
@SecondaryTable(name = "orders_products")
public class Order {
    @Column(name = "date")
    private LocalDate date;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ElementCollection
    @CollectionTable(name = "orders_products")
    @Column(name = "product_amount")
    @MapKeyJoinColumn(name = "product_id")
    private Map<Product, Integer> products;
    @Column(name = "user_id")
    private int userId;

    public Order(LocalDate date, Map<Product, Integer> products, int userId) {
        this.date = date;
        this.products = products;
        this.userId = userId;
    }
}
