package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.entity.Product;
import by.tms.eshopspringboot.service.CategoryServiceAware;
import by.tms.eshopspringboot.service.ProductServiceAware;
import by.tms.eshopspringboot.utils.SearchParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static by.tms.eshopspringboot.utils.Constants.Attributes.CATEGORIES;
import static by.tms.eshopspringboot.utils.Constants.Attributes.CURRENT_PAGE;
import static by.tms.eshopspringboot.utils.Constants.Attributes.PRODUCTS;
import static by.tms.eshopspringboot.utils.Constants.Attributes.SEARCH_PARAMS;
import static by.tms.eshopspringboot.utils.Constants.Attributes.TOTAL_PAGES;
import static by.tms.eshopspringboot.utils.Constants.MappingPath.SEARCH;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
@Slf4j
public class SearchController {
    private final ProductServiceAware productService;
    private final CategoryServiceAware categoryService;
    private static final int PAGE_SIZE = 2;

    @GetMapping
    public ModelAndView searchResult(SearchParams searchParams, @RequestParam Optional<Integer> page) {
        ModelAndView modelAndView = new ModelAndView();

        Pageable pageable = page.map(integer -> PageRequest.of(integer, PAGE_SIZE)).orElseGet(() -> PageRequest.of(0, PAGE_SIZE));
        Page<Product> searchResult = productService.searchByParamsAndPageNumber(searchParams, pageable);

        modelAndView.addObject(CATEGORIES, categoryService.getCategories());
        modelAndView.addObject(PRODUCTS, searchResult.get().toList());
        modelAndView.addObject(TOTAL_PAGES, searchResult.getTotalPages());
        modelAndView.addObject(CURRENT_PAGE, searchResult.getNumber());

        modelAndView.addObject(SEARCH_PARAMS, searchParams);

        modelAndView.setViewName(SEARCH);
        return modelAndView;
    }
}
