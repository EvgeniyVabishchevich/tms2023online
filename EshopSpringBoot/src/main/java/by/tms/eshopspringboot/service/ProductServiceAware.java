package by.tms.eshopspringboot.service;

import by.tms.eshopspringboot.dto.SearchParams;
import by.tms.eshopspringboot.entity.Product;
import by.tms.eshopspringboot.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductServiceAware {

    void saveProduct(Product product);

    Product findById(Long id) throws NotFoundException;

    Map<Product, Integer> getProductsByIds(Map<Long, Integer> idAmountMap) throws NotFoundException;

    List<Product> searchByParams(SearchParams searchParams);

    Page<Product> searchByParamsAndPageNumber(SearchParams searchParams, Pageable pageable);
}
