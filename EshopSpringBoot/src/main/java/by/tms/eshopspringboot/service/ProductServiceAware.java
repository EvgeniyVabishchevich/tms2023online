package by.tms.eshopspringboot.service;

import by.tms.eshopspringboot.dto.ProductDTO;
import by.tms.eshopspringboot.dto.SearchParams;
import by.tms.eshopspringboot.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductServiceAware {

    void saveProduct(ProductDTO productDTO);

    ProductDTO findById(Long id) throws NotFoundException;

    Map<ProductDTO, Integer> getProductsByIds(Map<Long, Integer> idAmountMap) throws NotFoundException;

    List<ProductDTO> searchByParams(SearchParams searchParams);

    Page<ProductDTO> searchByParamsAndPageNumber(SearchParams searchParams, Pageable pageable);
}
