package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.entity.Category;
import by.tms.eshopspringboot.exception.NotFoundException;
import by.tms.eshopspringboot.service.CategoryServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import static by.tms.eshopspringboot.utils.Constants.MappingPath.PRODUCTS;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final CategoryServiceAware categoryService;

    @GetMapping("/{categoryName}")
    public ModelAndView getCategoryProducts(@PathVariable String categoryName) throws NotFoundException {
        ModelAndView modelAndView = new ModelAndView();

        Category category = categoryService.findCategoryByName(categoryName);

        modelAndView.addObject("category", category);
        modelAndView.setViewName(PRODUCTS);

        return modelAndView;
    }
}
