package by.tms.eshopspringboot.service;

import by.tms.eshopspringboot.entity.Product;
import by.tms.eshopspringboot.exception.NotFoundException;
import by.tms.eshopspringboot.utils.SearchParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductServiceAware {

    void saveProduct(Product product);

    Product findById(int id) throws NotFoundException;

    Map<Product, Integer> getProductsByIds(Map<Integer, Integer> idAmountMap) throws NotFoundException;

    List<Product> searchByParams(SearchParams searchParams);

    Page<Product> searchByParamsAndPageNumber(SearchParams searchParams, Pageable pageable);
}
