package by.tms.eshopspringboot.service.impl;

import by.tms.eshopspringboot.entity.Product;
import by.tms.eshopspringboot.repository.impl.ProductRepositoryImpl;
import by.tms.eshopspringboot.service.ProductServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductServiceAware {
    private final ProductRepositoryImpl productRepositoryImpl;

    @Override
    public void saveProduct(Product product) {
        productRepositoryImpl.save(product);
    }

    @Override
    public Product findById(int id) {
        return productRepositoryImpl.findById(id).orElseThrow();
    }

    @Override
    public List<Product> getProductsByTextInNameAndDescription(String searchRequest) {
        return productRepositoryImpl.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchRequest, searchRequest);
    }

    @Override
    public Map<Product, Integer> getProductsByIds(Map<Integer, Integer> idAmountMap) {
        Map<Product, Integer> productsMap = new HashMap<>();

        idAmountMap.keySet().forEach(id -> {
            productsMap.put(findById(id), idAmountMap.get(id));
        });

        return productsMap;
    }
}
