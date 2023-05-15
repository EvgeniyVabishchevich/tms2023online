package by.tms.eshopspringboot.service;

import by.tms.eshopspringboot.entity.Category;
import exception.NotFoundException;

import java.util.List;

public interface CategoryServiceAware {

    void saveCategory(Category category);

    Category findById(int id) throws NotFoundException;

    List<Category> getCategories();

    Category findCategoryByName(String name) throws NotFoundException;
}
