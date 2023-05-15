package by.tms.eshopspringboot.service.impl;

import by.tms.eshopspringboot.entity.Product;
import by.tms.eshopspringboot.repository.ProductRepository;
import by.tms.eshopspringboot.service.ProductServiceAware;
import exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.tms.eshopspringboot.utils.ThrowingConsumer.throwingConsumerWrapper;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductServiceAware {
    private final ProductRepository productRepository;

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product findById(int id) throws NotFoundException {
        return productRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Cannot find product by id = %d", id)));
    }

    @Override
    public List<Product> getProductsByTextInNameAndDescription(String searchRequest) {
        return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchRequest, searchRequest);
    }

    @Override
    public Map<Product, Integer> getProductsByIds(Map<Integer, Integer> idAmountMap) {
        Map<Product, Integer> productsMap = new HashMap<>();

        idAmountMap.keySet().forEach(throwingConsumerWrapper(id -> productsMap.put(findById(id), idAmountMap.get(id))));

        return productsMap;
    }
}
