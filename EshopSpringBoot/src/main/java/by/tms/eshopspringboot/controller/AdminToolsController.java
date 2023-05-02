package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.model.Product;
import by.tms.eshopspringboot.model.enums.Page;
import by.tms.eshopspringboot.service.CategoryServiceAware;
import by.tms.eshopspringboot.service.ImageServiceAware;
import by.tms.eshopspringboot.service.ProductServiceAware;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import static by.tms.eshopspringboot.model.enums.RequestParamsConstants.CATEGORY;
import static by.tms.eshopspringboot.model.enums.RequestParamsConstants.DESCRIPTION;
import static by.tms.eshopspringboot.model.enums.RequestParamsConstants.IMAGE;
import static by.tms.eshopspringboot.model.enums.RequestParamsConstants.NAME;
import static by.tms.eshopspringboot.model.enums.RequestParamsConstants.PRICE;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Controller
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class AdminToolsController {
    private final CategoryServiceAware categoryService;
    private final ImageServiceAware imageService;
    private final ProductServiceAware productService;

    @GetMapping
    public ModelAndView showPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryService.getCategories());
        modelAndView.setViewName(Page.ADMIN.getValue());
        return modelAndView;
    }

    @PostMapping(value = "/new-category")
    @ResponseStatus(value = OK)
    public void createNewCategory(@RequestParam(IMAGE) MultipartFile image, @RequestParam(NAME) String name) {
        try (InputStream fileStream = image.getInputStream()) {
            byte[] imageBytes = fileStream.readAllBytes();

            int imageId = imageService.addImage(image.getContentType(), imageBytes);

            categoryService.addCategory(name, imageId);
        } catch (IOException e) {
            log.error("Error, while getting image from request", e);
        }
    }

    @PostMapping(value = "/new-product")
    @ResponseStatus(value = OK)
    public void execute(@RequestParam(IMAGE) MultipartFile image, @RequestParam(NAME) String name,
                        @RequestParam(DESCRIPTION) String description, @RequestParam(CATEGORY) String category,
                        @RequestParam(PRICE) String price) {
        try (InputStream fileStream = image.getInputStream()) {
            byte[] imageBytes = fileStream.readAllBytes();

            int imageId = imageService.addImage(image.getContentType(), imageBytes);

            Product product = new Product(name, description, new BigDecimal(price), imageId, categoryService.getCategoryId(category));

            productService.addProduct(product);
        } catch (IOException e) {
            log.error("Error, while getting image from request", e);
        }
    }
}
