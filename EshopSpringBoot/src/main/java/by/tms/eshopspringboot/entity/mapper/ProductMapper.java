package by.tms.eshopspringboot.entity.mapper;

import by.tms.eshopspringboot.dto.ProductDTO;
import by.tms.eshopspringboot.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO productToProductDTO(Product product);

    Product productDTOToProduct(ProductDTO productDTO);
}
