package by.tms.eshopspringboot.repository.impl;

import by.tms.eshopspringboot.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Objects;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ImageRepositoryImpl implements ImageRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public int addImage(String imageContentType, byte[] imageBytes) {
        String sql = "INSERT INTO images (content_type, image) VALUES (?, ?) RETURNING id";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, imageContentType);
            preparedStatement.setBytes(2, imageBytes);

            return preparedStatement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public byte[] getImageById(int id) {
        String sql = "SELECT image FROM images WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, byte[].class, id);
    }

    @Override
    public String getImageContentTypeById(int id) {
        String sql = "SELECT content_type FROM images WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, String.class, id);
    }
}
