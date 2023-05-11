package by.tms.eshopspringboot.repository.impl;

import by.tms.eshopspringboot.entity.Category;
import org.springframework.data.repository.ListCrudRepository;

public interface CategoryRepositoryImpl extends ListCrudRepository<Category, Integer> {
    Category findCategoryByName(String name);
}
