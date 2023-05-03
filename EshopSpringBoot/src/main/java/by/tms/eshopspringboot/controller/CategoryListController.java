package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.service.CategoryServiceAware;
import by.tms.eshopspringboot.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static by.tms.eshopspringboot.utils.Constants.Attributes.CATEGORIES;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryListController {
    private final CategoryServiceAware categoryService;

    @GetMapping
    public ModelAndView setCategoryList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(CATEGORIES, categoryService.getCategories());
        modelAndView.setViewName(Constants.MappingPath.CATEGORIES);
        return modelAndView;
    }
}
