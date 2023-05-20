package by.tms.eshopspringboot.service.impl;

import by.tms.eshopspringboot.entity.Product;
import by.tms.eshopspringboot.repository.ProductRepository;
import by.tms.eshopspringboot.service.ProductServiceAware;
import by.tms.eshopspringboot.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
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
    public Product findById(int id) throws NotFoundException {
        return productRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Cannot find product by id = %d", id)));
    }

    @Override
    public List<Product> getProductsByTextInNameAndDescription(String searchRequest) {
        return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchRequest, searchRequest);
    }

    @Override
    public Map<Product, Integer> getProductsByIds(Map<Integer, Integer> idToAmount) throws NotFoundException {
        Map<Product, Integer> productsMap = new HashMap<>();

       /* List<Product> products = productRepository.findByIdIn(new ArrayList<>(idToAmount.keySet()));
        products.forEach(product -> productsMap.put(product, idToAmount.get(product.getId())));*/

        for (Map.Entry<Integer, Integer> entry : idToAmount.entrySet()) {
            productsMap.put(findById(entry.getKey()), entry.getValue());
        }

        return productsMap;
    }
}
