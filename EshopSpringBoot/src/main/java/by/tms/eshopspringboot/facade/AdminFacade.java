package by.tms.eshopspringboot.facade;

import by.tms.eshopspringboot.entity.Category;
import by.tms.eshopspringboot.entity.Image;
import by.tms.eshopspringboot.entity.Product;
import by.tms.eshopspringboot.exception.NotFoundException;
import by.tms.eshopspringboot.service.CategoryServiceAware;
import by.tms.eshopspringboot.service.ImageServiceAware;
import by.tms.eshopspringboot.service.ProductServiceAware;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

@Slf4j
@Component
@Transactional
public class AdminFacade {
    private CategoryServiceAware categoryService;
    private ImageServiceAware imageService;
    private ProductServiceAware productService;

    public void addCategory(MultipartFile image, String name) {
        try (InputStream fileStream = image.getInputStream()) {
            byte[] imageBytes = fileStream.readAllBytes();

            Image newImage = new Image(image.getContentType(), imageBytes);

            Long imageId = imageService.saveImage(newImage);
            Category category = new Category(name, imageId);

            categoryService.saveCategory(category);
        } catch (IOException e) {
            log.error("Error, while getting image from request", e);
        }
    }

    public void addProduct(MultipartFile image, String name, String description, String category, String price) throws NotFoundException {
        try (InputStream fileStream = image.getInputStream()) {
            byte[] imageBytes = fileStream.readAllBytes();

            Image newImage = new Image(image.getContentType(), imageBytes);

            Long imageId = imageService.saveImage(newImage);

            Product product = new Product(name, description, new BigDecimal(price), imageId, categoryService.findCategoryByName(category));

            productService.saveProduct(product);
        } catch (IOException e) {
            log.error("Error, while getting image from request", e);
        }
    }

    @Autowired
    public void setCategoryService(CategoryServiceAware categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setImageService(ImageServiceAware imageService) {
        this.imageService = imageService;
    }

    @Autowired
    public void setProductService(ProductServiceAware productService) {
        this.productService = productService;
    }
}