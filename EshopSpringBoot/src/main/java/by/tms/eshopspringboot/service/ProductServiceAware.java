package by.tms.eshopspringboot.service;

import by.tms.eshopspringboot.entity.Product;
import exception.NotFoundException;

import java.util.List;
import java.util.Map;

public interface ProductServiceAware {

    void saveProduct(Product product);

    Product findById(int id) throws NotFoundException;

    Map<Product, Integer> getProductsByIds(Map<Integer, Integer> idAmountMap);

    List<Product> getProductsByTextInNameAndDescription(String searchRequest);
}
