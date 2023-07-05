package by.tms.eshopspringboot.service;

import by.tms.eshopspringboot.dto.CategoryDTO;
import by.tms.eshopspringboot.exception.NotFoundException;

import java.util.List;

public interface CategoryServiceAware {

    void saveCategory(CategoryDTO categoryDTO);

    CategoryDTO findById(Long id) throws NotFoundException;

    List<CategoryDTO> getCategories();

    CategoryDTO findCategoryByName(String name) throws NotFoundException;
}
