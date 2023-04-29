package com.tms.webshop.service;

import com.tms.webshop.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProductServiceAware {

    void addProduct(Product product);

    List<Product> getProductsByCategoryId(int categoryId);

    Product getProductById(int id);

    Map<Product, Integer> getProductsByIds(HashMap<Integer, Integer> idAmountMap);

    List<Product> getProductsByTextInNameAndDescription(String searchRequest);
}
