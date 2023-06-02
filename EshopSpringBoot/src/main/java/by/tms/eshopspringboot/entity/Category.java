package by.tms.eshopspringboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "image_id")
    private Long imageId;
    @OneToMany
    @JoinColumn(name = "category_id")
    private List<Product> products;

    public Category(String name, Long imageId, List<Product> products) {
        this.name = name;
        this.imageId = imageId;
        this.products = products;
    }

    public Category(String name, Long imageId) {
        this.name = name;
        this.imageId = imageId;
    }
}

