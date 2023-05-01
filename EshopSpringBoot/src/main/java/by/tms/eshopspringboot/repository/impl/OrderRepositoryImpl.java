package by.tms.eshopspringboot.repository.impl;

import by.tms.eshopspringboot.model.Order;
import by.tms.eshopspringboot.model.Product;
import by.tms.eshopspringboot.repository.OrderRepository;
import by.tms.eshopspringboot.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ProductRepository productRepository;

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        String sql = "SELECT * FROM orders WHERE user_id = ?";

        return jdbcTemplate.query(sql, new OrderRowMapper(), userId);
    }

    @Override
    public void saveOrder(int userId, Order order) {
        HashMap<String, String> orderProductsMap = new HashMap<>();
        order.getProducts().keySet().forEach(product -> {
            orderProductsMap.put(String.valueOf(product.getId()), String.valueOf(order.getProducts().get(product)));
        });

        String sql = "INSERT INTO orders (user_id, date, products) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, userId, Date.valueOf(order.getDate()), orderProductsMap);
    }

    private class OrderRowMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            order.setId(rs.getInt(1));
            order.setDate(rs.getDate(3).toLocalDate());

            Map<String, String> productsMap = (Map<String, String>) rs.getObject(4);
            Map<Product, Integer> products = new HashMap<>();
            for (String id : productsMap.keySet()) {
                products.put(productRepository.getProductById(Integer.parseInt(id)), Integer.parseInt(productsMap.get(id)));
            }
            order.setProducts(products);

            return order;
        }
    }
}
