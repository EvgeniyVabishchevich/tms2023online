package by.tms.eshopspringboot.repository;

import by.tms.eshopspringboot.model.Product;

import java.util.List;

public interface ProductRepository {
    void addProduct(Product product);

    List<Product> getProductsByCategoryId(int categoryId);

    Product getProductById(int id);

    List<Product> getProductsByTextInNameAndDescription(String searchRequest);
}
