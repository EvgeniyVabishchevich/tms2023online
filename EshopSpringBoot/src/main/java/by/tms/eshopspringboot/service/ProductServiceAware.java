package by.tms.eshopspringboot.service;

import by.tms.eshopspringboot.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductServiceAware {

    void saveProduct(Product product);

    Product findById(int id) throws Exception;

    Map<Product, Integer> getProductsByIds(Map<Integer, Integer> idAmountMap);

    List<Product> getProductsByTextInNameAndDescription(String searchRequest);
}
