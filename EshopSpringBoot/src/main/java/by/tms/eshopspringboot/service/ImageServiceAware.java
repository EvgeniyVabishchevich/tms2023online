package by.tms.eshopspringboot.service;

public interface ImageServiceAware {
    void addImage(String imageName, byte[] imageBytes);

    byte[] getImageByName(String name);
}
