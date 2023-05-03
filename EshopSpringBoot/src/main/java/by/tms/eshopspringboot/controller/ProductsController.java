package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.model.Category;
import by.tms.eshopspringboot.service.CategoryServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static by.tms.eshopspringboot.utils.Constants.MappingPath.ERROR404;
import static by.tms.eshopspringboot.utils.Constants.MappingPath.PRODUCTS;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final CategoryServiceAware categoryService;

    @GetMapping("/{categoryName}")
    public ModelAndView getCategoryProducts(@PathVariable String categoryName) {
        ModelAndView modelAndView = new ModelAndView();

        Category category = categoryService.getCategoryByName(categoryName);

        if (category == null) {
            modelAndView.addObject("errorMsg", "No such category");
            modelAndView.setViewName(ERROR404);
        } else {
            modelAndView.addObject("category", category);
            modelAndView.setViewName(PRODUCTS);
        }
        return modelAndView;
    }
}
