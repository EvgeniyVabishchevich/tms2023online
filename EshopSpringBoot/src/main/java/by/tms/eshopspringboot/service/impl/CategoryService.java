package by.tms.eshopspringboot.service.impl;

import by.tms.eshopspringboot.entity.Category;
import by.tms.eshopspringboot.repository.impl.CategoryRepositoryImpl;
import by.tms.eshopspringboot.service.CategoryServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServiceAware {
    private final CategoryRepositoryImpl categoryRepositoryImpl;

    @Override
    public void saveCategory(Category category) {
        categoryRepositoryImpl.save(category);
    }

    @Override
    public int getCategoryId(String name) {
        return categoryRepositoryImpl.findCategoryByName(name).getId();
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepositoryImpl.findAll();
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepositoryImpl.findCategoryByName(name);
    }

    @Override
    public String getCategoryNameById(int id) {
        return categoryRepositoryImpl.findById(id).orElseThrow().getName();
    }
}
