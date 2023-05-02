package by.tms.eshopspringboot.repository;

public interface ImageRepository {
    int addImage(String imageContentType, byte[] imageBytes);

    byte[] getImageById(int id);

    String getImageContentTypeById(int id);
}
