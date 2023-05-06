package by.tms.eshopspringboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Category {
    private int id;
    private String name;
    private int imageId;
    private List<Product> productList;

    public Category(int id, String name, int imageId, List<Product> productList) {
        this.id = id;
        this.name = name;
        this.imageId = imageId;
        this.productList = productList;
    }

    public Category(String name, int imageId, List<Product> productList) {
        this.name = name;
        this.imageId = imageId;
        this.productList = productList;
    }

    public Category(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }
}

