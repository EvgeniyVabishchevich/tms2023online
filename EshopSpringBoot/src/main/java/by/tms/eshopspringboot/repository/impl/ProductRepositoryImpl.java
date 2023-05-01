package by.tms.eshopspringboot.repository.impl;

import by.tms.eshopspringboot.model.Product;
import by.tms.eshopspringboot.repository.ProductRepository;
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
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void addProduct(Product product) {
        String sql = "INSERT INTO products (name, description, price, image_name, category_id) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, product.getName(), product.getDescription(), product.getPrice(), product.getImageName(),
                product.getCategoryId());
    }

    @Override
    public List<Product> getProductsByCategoryId(int categoryId) {
        String sql = "SELECT * FROM products WHERE category_id = ?";

        return jdbcTemplate.query(sql, new ProductRowMapper(), categoryId);
    }

    public Product getProductById(int productId) {
        String sql = "SELECT * FROM products WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new ProductRowMapper(), productId);
    }

    @Override
    public List<Product> getProductsByTextInNameAndDescription(String searchRequest) {
        String sql = "SELECT * FROM products WHERE name LIKE ? OR description LIKE ?";
        String searchPattern = "%" + searchRequest + "%";

        return jdbcTemplate.query(sql, new ProductRowMapper(), searchPattern, searchPattern);
    }

    public static class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

            Product product = new Product();

            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setPrice(rs.getBigDecimal("price"));
            product.setImageName(rs.getString("image_name"));
            product.setCategoryId(rs.getInt("category_id"));

            return product;
        }
    }
}
