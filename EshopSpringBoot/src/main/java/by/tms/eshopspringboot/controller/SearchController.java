package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.model.Product;
import by.tms.eshopspringboot.model.enums.RequestParamsConstants;
import by.tms.eshopspringboot.service.CategoryServiceAware;
import by.tms.eshopspringboot.service.ProductServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

import static by.tms.eshopspringboot.model.enums.Page.SEARCH;

@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private final ProductServiceAware productService;
    private final CategoryServiceAware categoryService;

    private static final String ALL_CATEGORIES = "All";

    @GetMapping
    @ResponseBody
    public ModelAndView searchResult(@RequestParam(name = (RequestParamsConstants.SEARCH_REQUEST), defaultValue = "")
                                     String searchRequest,
                                     @RequestParam(name = (RequestParamsConstants.MIN_PRICE), defaultValue = "0") String minPrice,
                                     @RequestParam(name = (RequestParamsConstants.MAX_PRICE), defaultValue = "-1") String maxPrice,
                                     @RequestParam(name = (RequestParamsConstants.CATEGORY), defaultValue = ALL_CATEGORIES) String category) {
        ModelAndView modelAndView = new ModelAndView();

        List<Product> searchResult = productService.getProductsByTextInNameAndDescription(searchRequest);

        searchResult = searchResult.stream()
                .filter(product -> {
                    boolean notTooSmallPrice = product.getPrice().compareTo(new BigDecimal(minPrice)) >= 0;
                    boolean notTooBigPrice = new BigDecimal(maxPrice).compareTo(BigDecimal.ZERO) < 0
                            || product.getPrice().compareTo(new BigDecimal(maxPrice)) <= 0;
                    boolean isCategoryOk = category.equals(ALL_CATEGORIES)
                            || categoryService.getCategoryNameById(product.getCategoryId()).equals(category);

                    return notTooSmallPrice && notTooBigPrice && isCategoryOk;
                }).toList();

        modelAndView.addObject("categories", categoryService.getCategories());
        modelAndView.addObject("products", searchResult);
        modelAndView.setViewName(SEARCH.getValue());
        return modelAndView;
    }
}
