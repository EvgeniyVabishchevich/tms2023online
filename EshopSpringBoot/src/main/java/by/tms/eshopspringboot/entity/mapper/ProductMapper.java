package by.tms.eshopspringboot.entity.mapper;

import by.tms.eshopspringboot.dto.ProductDTO;
import by.tms.eshopspringboot.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
    @Mapping(target = "categoryDTO", source = "category")
    ProductDTO productToProductDTO(Product product);

    @Mapping(target = "category", source = "categoryDTO")
    Product productDTOToProduct(ProductDTO productDTO);
}
