package by.tms.eshopspringboot.repository.impl;

import by.tms.eshopspringboot.model.Category;
import by.tms.eshopspringboot.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ProductRepositoryImpl productRepositoryImpl;

    @Override
    public void addCategory(String name, int imageId) {
        String sql = "INSERT INTO categories (name, image_id) VALUES (?, ?)";

        jdbcTemplate.update(sql, name, imageId);
    }

    @Override
    public List<Category> getCategories() {
        String sql = "SELECT * FROM categories";

        return jdbcTemplate.query(sql, new CategoryRowMapper());
    }

    @Override
    public Category getCategoryByName(String name) {
        String sql = "SELECT * FROM categories WHERE name = ?";

        return jdbcTemplate.queryForObject(sql, new CategoryRowMapper(), name);
    }

    @Override
    public Category getCategoryById(int id) {
        String sql = "SELECT * FROM categories WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new CategoryRowMapper(), id);
    }

    private class CategoryRowMapper implements RowMapper<Category> {
        @Override
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            Category category = new Category();

            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            category.setImageId(rs.getInt("image_id"));
            category.setProductList(productRepositoryImpl.findByCategoryId(category.getId()));

            return category;
        }
    }

}
