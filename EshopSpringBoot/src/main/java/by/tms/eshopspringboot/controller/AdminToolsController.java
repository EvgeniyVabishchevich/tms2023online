package by.tms.eshopspringboot.controller;

import by.tms.eshopspringboot.entity.Category;
import by.tms.eshopspringboot.entity.Image;
import by.tms.eshopspringboot.entity.Product;
import by.tms.eshopspringboot.service.CategoryServiceAware;
import by.tms.eshopspringboot.service.ImageServiceAware;
import by.tms.eshopspringboot.service.ProductServiceAware;
import exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

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
    private final CategoryServiceAware categoryService;
    private final ImageServiceAware imageService;
    private final ProductServiceAware productService;

    @GetMapping
    public ModelAndView showPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryService.getCategories());
        modelAndView.setViewName(ADMIN);
        return modelAndView;
    }

    @PostMapping(value = "/new-category")
    public void createNewCategory(@RequestParam(IMAGE) MultipartFile image, @RequestParam(NAME) String name) {
        try (InputStream fileStream = image.getInputStream()) {
            byte[] imageBytes = fileStream.readAllBytes();

            Image newImage = new Image(image.getContentType(), imageBytes);

            int imageId = imageService.saveImage(newImage);

            Category category = new Category(name, imageId);

            categoryService.saveCategory(category);
        } catch (IOException e) {
            log.error("Error, while getting image from request", e);
        }
    }

    @PostMapping(value = "/new-product")
    public void createNewProduct(@RequestParam(IMAGE) MultipartFile image, @RequestParam(NAME) String name,
                                 @RequestParam(DESCRIPTION) String description, @RequestParam(CATEGORY) String category,
                                 @RequestParam(PRICE) String price) throws NotFoundException {
        try (InputStream fileStream = image.getInputStream()) {
            byte[] imageBytes = fileStream.readAllBytes();

            Image newImage = new Image(image.getContentType(), imageBytes);

            int imageId = imageService.saveImage(newImage);

            Product product = new Product(name, description, new BigDecimal(price), imageId, categoryService.findCategoryByName(category).getId());

            productService.saveProduct(product);
        } catch (IOException e) {
            log.error("Error, while getting image from request", e);
        }
    }
}
