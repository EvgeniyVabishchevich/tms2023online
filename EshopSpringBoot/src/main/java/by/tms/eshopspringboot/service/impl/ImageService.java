package by.tms.eshopspringboot.service.impl;

import by.tms.eshopspringboot.entity.Image;
import by.tms.eshopspringboot.repository.impl.ImageRepositoryImpl;
import by.tms.eshopspringboot.service.ImageServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService implements ImageServiceAware {
    private final ImageRepositoryImpl imageRepositoryImpl;

    @Override
    public int saveImage(Image image) {
        Image savedImage = imageRepositoryImpl.save(image);
        return savedImage.getId();
    }

    @Override
    public Image findById(int id) {
        return imageRepositoryImpl.findById(id).orElseThrow();
    }

    @Override
    public String getImageContentTypeById(int id) {
        return imageRepositoryImpl.findById(id).orElseThrow().getContentType();
    }
}
