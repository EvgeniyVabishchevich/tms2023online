package by.tms.eshopspringboot.dto;

import by.tms.eshopspringboot.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private Long imageId;
    private List<Product> products;

    public CategoryDTO(String name, Long imageId, List<Product> products) {
        this.name = name;
        this.imageId = imageId;
        this.products = products;
    }

    public CategoryDTO(String name, Long imageId) {
        this.name = name;
        this.imageId = imageId;
    }
}
