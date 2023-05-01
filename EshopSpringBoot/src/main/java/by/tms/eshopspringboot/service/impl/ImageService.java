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
    public void addImage(String imageName, byte[] imageBytes) {
        imageRepository.addImage(imageName, imageBytes);
    }

    @Override
    public byte[] getImageByName(String name) {
        return imageRepository.getImageByName(name);
    }
}
