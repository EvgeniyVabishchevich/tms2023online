package by.tms.eshopspringboot.repository.impl;

import by.tms.eshopspringboot.entity.Product;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositoryImpl extends ListCrudRepository<Product, Integer> {
    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String nameText, String descriptionText);

    List<Product> findByCategoryId(int categoryId);
}
