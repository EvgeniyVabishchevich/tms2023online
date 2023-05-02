package by.tms.eshopspringboot.service;

public interface ImageServiceAware {
    int addImage(String imageContentType, byte[] imageBytes);

    byte[] getImageById(int id);

    String getImageContentTypeById(int id);
}
