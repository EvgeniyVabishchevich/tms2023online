package by.tms.eshopspringboot.service.impl;

import by.tms.eshopspringboot.repository.ImageRepository;
import by.tms.eshopspringboot.service.ImageServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService implements ImageServiceAware {
    private final ImageRepository imageRepository;

    @Override
    public int addImage(String imageContentType, byte[] imageBytes) {
        return imageRepository.addImage(imageContentType, imageBytes);
    }

    @Override
    public byte[] getImageById(int id) {
        return imageRepository.getImageById(id);
    }

    @Override
    public String getImageContentTypeById(int id) {
        return imageRepository.getImageContentTypeById(id);
    }
}
