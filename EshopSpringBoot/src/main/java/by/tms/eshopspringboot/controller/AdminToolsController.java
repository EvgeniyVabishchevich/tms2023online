package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.exception.NotFoundException;
import by.tms.eshopspringboot.facade.AdminFacade;
import by.tms.eshopspringboot.service.CategoryServiceAware;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import static by.tms.eshopspringboot.utils.Constants.MappingPath.ADMIN;
import static by.tms.eshopspringboot.utils.Constants.RequestParameters.CATEGORY;
import static by.tms.eshopspringboot.utils.Constants.RequestParameters.DESCRIPTION;
import static by.tms.eshopspringboot.utils.Constants.RequestParameters.IMAGE;
import static by.tms.eshopspringboot.utils.Constants.RequestParameters.NAME;
import static by.tms.eshopspringboot.utils.Constants.RequestParameters.PRICE;

@Slf4j
@RestController
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class AdminToolsController {
    private final AdminFacade adminFacade;
    private final CategoryServiceAware categoryService;

    @GetMapping
    public ModelAndView showPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryService.getCategories());
        modelAndView.setViewName(ADMIN);
        return modelAndView;
    }

    @PostMapping(value = "/new-category")
    public void createNewCategory(@RequestParam(IMAGE) MultipartFile image, @RequestParam(NAME) String name) {
        adminFacade.addCategory(image, name);
    }

    @PostMapping(value = "/new-product")
    public void createNewProduct(@RequestParam(IMAGE) MultipartFile image, @RequestParam(NAME) String name,
                                 @RequestParam(DESCRIPTION) String description, @RequestParam(CATEGORY) String category,
                                 @RequestParam(PRICE) String price) throws NotFoundException {
        adminFacade.addProduct(image, name, description, category, price);
    }
}
