package by.tms.eshopspringboot.repository.impl;

import by.tms.eshopspringboot.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ImageRepositoryImpl implements ImageRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void addImage(String imageName, byte[] imageBytes) {
        String sql = "INSERT INTO images (name, image) VALUES (?, ?)";

        jdbcTemplate.update(sql, imageName, imageBytes);
    }

    @Override
    public byte[] getImageByName(String name) {
        String sql = "SELECT image FROM images WHERE name = ?";

        return jdbcTemplate.queryForObject(sql, byte[].class, name);
    }
}
