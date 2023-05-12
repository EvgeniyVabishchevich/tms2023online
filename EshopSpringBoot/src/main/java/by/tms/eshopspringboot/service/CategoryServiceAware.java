package by.tms.eshopspringboot.service;

import by.tms.eshopspringboot.entity.Category;

import java.util.List;

public interface CategoryServiceAware {

    void saveCategory(Category category);

    Category findById(int id) throws Exception;

    List<Category> getCategories();

    Category findCategoryByName(String name) throws Exception;
}
