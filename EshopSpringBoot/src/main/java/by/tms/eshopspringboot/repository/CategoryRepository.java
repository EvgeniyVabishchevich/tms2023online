package by.tms.eshopspringboot.repository;

import by.tms.eshopspringboot.entity.Category;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface CategoryRepository extends ListCrudRepository<Category, Long> {
    Optional<Category> findCategoryByName(String name);
}
