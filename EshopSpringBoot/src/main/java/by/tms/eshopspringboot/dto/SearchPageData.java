package by.tms.eshopspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SearchPageData {
    private final List<CategoryDTO> categories;
    private final List<ProductDTO> products;
    private final int currentPage;
    private final int totalPages;
    private final SearchParams searchParams;
}
