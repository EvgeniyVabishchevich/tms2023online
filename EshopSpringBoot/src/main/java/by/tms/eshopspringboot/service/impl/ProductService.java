package by.tms.eshopspringboot.service.impl;

import by.tms.eshopspringboot.dto.ProductDTO;
import by.tms.eshopspringboot.dto.SearchParams;
import by.tms.eshopspringboot.entity.Product;
import by.tms.eshopspringboot.entity.mapper.ProductMapper;
import by.tms.eshopspringboot.exception.NotFoundException;
import by.tms.eshopspringboot.repository.ProductRepository;
import by.tms.eshopspringboot.repository.specification.SearchProductSpecification;
import by.tms.eshopspringboot.service.ProductServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductServiceAware {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public void saveProduct(ProductDTO productDTO) {
        productRepository.save(productMapper.productDTOToProduct(productDTO));
    }

    @Override
    public ProductDTO findById(Long id) throws NotFoundException {
        return productMapper.productToProductDTO(productRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Cannot find product by id = %d", id))));
    }

    @Override
    public List<ProductDTO> searchByParams(SearchParams searchParams) {
        List<Product> productList = productRepository.findAll(new SearchProductSpecification(searchParams));

        return productList.stream()
                .map(productMapper::productToProductDTO).toList();
    }

    @Override
    public Page<ProductDTO> searchByParamsAndPageNumber(SearchParams searchParams, Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(new SearchProductSpecification(searchParams), pageable);

        return productPage.map(productMapper::productToProductDTO);
    }

    @Override
    public Map<ProductDTO, Integer> getProductsByIds(Map<Long, Integer> idToAmount) throws NotFoundException {
        Map<ProductDTO, Integer> productsMap = new HashMap<>();

        for (Map.Entry<Long, Integer> entry : idToAmount.entrySet()) {
            productsMap.put(findById(entry.getKey()), entry.getValue());
        }

        return productsMap;
    }
}
