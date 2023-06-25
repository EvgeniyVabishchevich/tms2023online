package by.tms.eshopspringboot.dto;

import by.tms.eshopspringboot.entity.Category;
import by.tms.eshopspringboot.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SearchPageData {
    private final List<Category> categories;
    private final List<Product> products;
    private final int currentPage;
    private final int totalPages;
    private final SearchParams searchParams;
}
