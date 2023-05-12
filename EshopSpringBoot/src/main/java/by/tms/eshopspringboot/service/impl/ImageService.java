package by.tms.eshopspringboot.service.impl;

import by.tms.eshopspringboot.entity.Image;
import by.tms.eshopspringboot.repository.ImageRepository;
import by.tms.eshopspringboot.service.ImageServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService implements ImageServiceAware {
    private final ImageRepository imageRepository;

    @Override
    public int saveImage(Image image) {
        Image savedImage = imageRepository.save(image);
        return savedImage.getId();
    }

    @Override
    public Image findById(int id) throws Exception {
        return imageRepository.findById(id).orElseThrow(
                () -> new Exception(String.format("Cannot find image by id = %d", id)));
    }
}
