package by.tms.eshopspringboot.service.impl;

import by.tms.eshopspringboot.entity.Product;
import by.tms.eshopspringboot.exception.NotFoundException;
import by.tms.eshopspringboot.repository.ProductRepository;
import by.tms.eshopspringboot.repository.specification.SearchProductSpecification;
import by.tms.eshopspringboot.service.ProductServiceAware;
import by.tms.eshopspringboot.dto.SearchParams;
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

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product findById(Long id) throws NotFoundException {
        return productRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Cannot find product by id = %d", id)));
    }

    @Override
    public List<Product> searchByParams(SearchParams searchParams) {
        return productRepository.findAll(new SearchProductSpecification(searchParams));
    }

    @Override
    public Page<Product> searchByParamsAndPageNumber(SearchParams searchParams, Pageable pageable) {
        return productRepository.findAll(new SearchProductSpecification(searchParams), pageable);
    }

    @Override
    public Map<Product, Integer> getProductsByIds(Map<Long, Integer> idToAmount) throws NotFoundException {
        Map<Product, Integer> productsMap = new HashMap<>();

       /* List<Product> products = productRepository.findByIdIn(new ArrayList<>(idToAmount.keySet()));
        products.forEach(product -> productsMap.put(product, idToAmount.get(product.getId())));*/

        for (Map.Entry<Long, Integer> entry : idToAmount.entrySet()) {
            productsMap.put(findById(entry.getKey()), entry.getValue());
        }

        return productsMap;
    }
}
