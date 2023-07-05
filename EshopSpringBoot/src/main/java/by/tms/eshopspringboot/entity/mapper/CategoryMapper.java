package by.tms.eshopspringboot.entity.mapper;

import by.tms.eshopspringboot.dto.CategoryDTO;
import by.tms.eshopspringboot.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO categoryToCategoryDTO(Category category);

    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}
