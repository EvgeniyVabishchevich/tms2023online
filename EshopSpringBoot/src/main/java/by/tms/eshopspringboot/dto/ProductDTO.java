package by.tms.eshopspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long imageId;
    private CategoryDTO categoryDTO;

    public ProductDTO(String name, String description, BigDecimal price, Long imageId, CategoryDTO categoryDTO) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageId = imageId;
        this.categoryDTO = categoryDTO;
    }
}
