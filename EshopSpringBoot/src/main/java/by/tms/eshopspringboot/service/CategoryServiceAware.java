package by.tms.eshopspringboot.service;

import by.tms.eshopspringboot.entity.Category;

import java.util.List;

public interface CategoryServiceAware {

    void saveCategory(Category category);

    int getCategoryId(String name);

    List<Category> getCategories();

    Category findCategoryByName(String name);

    String getCategoryNameById(int id);
}
