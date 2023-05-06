package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.entity.Product;
import by.tms.eshopspringboot.service.CategoryServiceAware;
import by.tms.eshopspringboot.service.ProductServiceAware;
import by.tms.eshopspringboot.utils.Constants.Attributes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

import static by.tms.eshopspringboot.utils.Constants.Attributes.CATEGORIES;
import static by.tms.eshopspringboot.utils.Constants.Attributes.PRODUCTS;
import static by.tms.eshopspringboot.utils.Constants.Attributes.SELECTED_CATEGORY;
import static by.tms.eshopspringboot.utils.Constants.MappingPath.SEARCH;
import static by.tms.eshopspringboot.utils.Constants.RequestParameters.CATEGORY;
import static by.tms.eshopspringboot.utils.Constants.RequestParameters.MAX_PRICE;
import static by.tms.eshopspringboot.utils.Constants.RequestParameters.MIN_PRICE;
import static by.tms.eshopspringboot.utils.Constants.RequestParameters.SEARCH_REQUEST;

@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private static final String ALL_CATEGORIES = "All";
    private static final String DEFAULT_MIN_VALUE = "0";
    private static final String DEFAULT_MAX_VALUE = "10000";
    private final ProductServiceAware productService;
    private final CategoryServiceAware categoryService;

    @GetMapping
    @ResponseBody
    public ModelAndView search(@RequestParam(name = SEARCH_REQUEST, defaultValue = "")
                                     String searchRequest,
                               @RequestParam(name = MIN_PRICE, defaultValue = DEFAULT_MIN_VALUE) String minPrice,
                               @RequestParam(name = MAX_PRICE, defaultValue = DEFAULT_MAX_VALUE) String maxPrice,
                               @RequestParam(name = CATEGORY, defaultValue = ALL_CATEGORIES) String category) {
        ModelAndView modelAndView = new ModelAndView();

        List<Product> searchResult = productService.getProductsByTextInNameAndDescription(searchRequest);

        searchResult = searchResult.stream()
                .filter(product -> {
                    boolean notTooSmallPrice = product.getPrice().compareTo(new BigDecimal(minPrice)) >= 0;
                    boolean notTooBigPrice = product.getPrice().compareTo(new BigDecimal(maxPrice)) <= 0;
                    boolean isCategoryOk = category.equals(ALL_CATEGORIES)
                            || categoryService.getCategoryNameById(product.getCategoryId()).equals(category);

                    return notTooSmallPrice && notTooBigPrice && isCategoryOk;
                }).toList();

        modelAndView.addObject(CATEGORIES, categoryService.getCategories());
        modelAndView.addObject(PRODUCTS, searchResult);

        modelAndView.addObject(Attributes.SEARCH_REQUEST, searchRequest);
        modelAndView.addObject(Attributes.MIN_PRICE, minPrice);
        modelAndView.addObject(Attributes.MAX_PRICE, maxPrice);
        modelAndView.addObject(SELECTED_CATEGORY, category);

        modelAndView.setViewName(SEARCH);
        return modelAndView;
    }
}
