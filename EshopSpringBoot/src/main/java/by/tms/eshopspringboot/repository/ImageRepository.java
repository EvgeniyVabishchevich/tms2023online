package by.tms.eshopspringboot.repository;

public interface ImageRepository {
    void addImage(String imageName, byte[] imageBytes);

    byte[] getImageByName(String name);
}
