package by.tms.eshopspringboot.service;

import by.tms.eshopspringboot.model.Category;

import java.util.List;

public interface CategoryServiceAware {

    void addCategory(String name, String imageName);

    int getCategoryId(String name);

    List<Category> getCategories();

    Category getCategoryByName(String name);

    String getCategoryNameById(int id);
}
