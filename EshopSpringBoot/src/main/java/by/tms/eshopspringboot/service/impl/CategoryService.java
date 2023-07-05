package by.tms.eshopspringboot.service.impl;

import by.tms.eshopspringboot.dto.CategoryDTO;
import by.tms.eshopspringboot.entity.Category;
import by.tms.eshopspringboot.entity.mapper.CategoryMapper;
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
    private final CategoryMapper categoryMapper;

    @Override
    public void saveCategory(CategoryDTO categoryDTO) {
        categoryRepository.save(categoryMapper.categoryDTOToCategory(categoryDTO));
    }

    @Override
    public CategoryDTO findById(Long id) throws NotFoundException {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Cannot find category by id = %d", id)));

        return categoryMapper.categoryToCategoryDTO(category);
    }

    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categoryList = categoryRepository.findAll();

        return categoryList.stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .toList();
    }

    @Override
    public CategoryDTO findCategoryByName(String name) throws NotFoundException {
        Category category = categoryRepository.findCategoryByName(name).orElseThrow(
                () -> new NotFoundException(String.format("Cannot find category by name = %s", name)));

        return categoryMapper.categoryToCategoryDTO(category);
    }
}
