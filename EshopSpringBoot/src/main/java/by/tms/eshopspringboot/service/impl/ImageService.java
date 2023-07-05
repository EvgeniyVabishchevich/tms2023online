package by.tms.eshopspringboot.service.impl;

import by.tms.eshopspringboot.entity.Image;
import by.tms.eshopspringboot.exception.NotFoundException;
import by.tms.eshopspringboot.repository.ImageRepository;
import by.tms.eshopspringboot.service.ImageServiceAware;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService implements ImageServiceAware {
    private final ImageRepository imageRepository;

    @Override
    public Long saveImage(MultipartFile image) throws IOException {
        try (InputStream fileStream = image.getInputStream()) {
            byte[] imageBytes = fileStream.readAllBytes();

            Image newImage = new Image(image.getContentType(), imageBytes);

            newImage = imageRepository.save(newImage);

            return newImage.getId();
        }
    }

    @Override
    public Image findById(Long id) throws NotFoundException {
        return imageRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Cannot find image by id = %d", id)));
    }
}
