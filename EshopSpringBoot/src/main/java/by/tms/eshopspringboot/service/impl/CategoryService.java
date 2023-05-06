package by.tms.eshopspringboot.service.impl;

import by.tms.eshopspringboot.entity.Category;
import by.tms.eshopspringboot.repository.CategoryRepository;
import by.tms.eshopspringboot.service.CategoryServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServiceAware {
    private final CategoryRepository categoryRepository;

    @Override
    public void addCategory(String name, int imageId) {
        categoryRepository.addCategory(name, imageId);
    }

    @Override
    public int getCategoryId(String name) {
        return categoryRepository.getCategoryByName(name).getId();
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.getCategories();
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name);
    }

    @Override
    public String getCategoryNameById(int id) {
        return categoryRepository.getCategoryById(id).getName();
    }
}
