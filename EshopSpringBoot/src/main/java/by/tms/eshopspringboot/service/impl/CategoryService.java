package by.tms.eshopspringboot.service.impl;

import by.tms.eshopspringboot.entity.Category;
import by.tms.eshopspringboot.exception.NotFoundException;
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
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) throws NotFoundException {
        return categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Cannot find category by id = %d", id)));
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryByName(String name) throws NotFoundException {
        return categoryRepository.findCategoryByName(name).orElseThrow(
                () -> new NotFoundException(String.format("Cannot find category by name = %s", name)));
    }
}
