package by.tms.eshopspringboot.service;

import by.tms.eshopspringboot.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProductServiceAware {

    void saveProduct(Product product);

    Product findById(int id);

    Map<Product, Integer> getProductsByIds(Map<Integer, Integer> idAmountMap);

    List<Product> getProductsByTextInNameAndDescription(String searchRequest);
}
