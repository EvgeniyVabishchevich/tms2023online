package by.tms.eshopspringboot.repository;

import by.tms.eshopspringboot.model.Category;

import java.util.List;

public interface CategoryRepository {
    void addCategory(String name, int imageId);

    List<Category> getCategories();

    Category getCategoryByName(String name);

    Category getCategoryById(int id);
}
