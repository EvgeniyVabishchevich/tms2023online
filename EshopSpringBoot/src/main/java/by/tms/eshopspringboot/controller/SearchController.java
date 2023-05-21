package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.entity.Product;
import by.tms.eshopspringboot.service.CategoryServiceAware;
import by.tms.eshopspringboot.service.ProductServiceAware;
import by.tms.eshopspringboot.utils.Constants.Attributes;
import by.tms.eshopspringboot.utils.SearchParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static by.tms.eshopspringboot.utils.Constants.Attributes.CATEGORIES;
import static by.tms.eshopspringboot.utils.Constants.Attributes.PRODUCTS;
import static by.tms.eshopspringboot.utils.Constants.Attributes.SELECTED_CATEGORY;
import static by.tms.eshopspringboot.utils.Constants.MappingPath.SEARCH;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
@Slf4j
public class SearchController {
    private final ProductServiceAware productService;
    private final CategoryServiceAware categoryService;

    @GetMapping
    public ModelAndView search(SearchParams searchParams) {
        ModelAndView modelAndView = new ModelAndView();

        List<Product> searchResult = productService.searchByParams(searchParams);

        modelAndView.addObject(CATEGORIES, categoryService.getCategories());
        modelAndView.addObject(PRODUCTS, searchResult);

        modelAndView.addObject(Attributes.SEARCH_REQUEST, searchParams.getSearchRequest());
        modelAndView.addObject(Attributes.MIN_PRICE, searchParams.getMinPrice());
        modelAndView.addObject(Attributes.MAX_PRICE, searchParams.getMaxPrice());
        modelAndView.addObject(SELECTED_CATEGORY, searchParams.getCategory());

        modelAndView.setViewName(SEARCH);
        return modelAndView;
    }
}
