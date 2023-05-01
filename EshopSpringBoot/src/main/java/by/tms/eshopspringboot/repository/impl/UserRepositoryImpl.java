package by.tms.eshopspringboot.repository.impl;

import by.tms.eshopspringboot.model.User;
import by.tms.eshopspringboot.model.enums.UserType;
import by.tms.eshopspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void addUser(User user, String password) {
        String sql = "INSERT INTO users (login, password, user_type, name, surname, email, birthday) " +
                "VALUES (?, ?, ?::privelege, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, user.getLogin(), user.getPassword(), user.getUserType(), user.getName(), user.getSurname(),
                user.getEmail(), Date.valueOf(user.getBirthday()));
    }

    @Override
    public User getUserByLogin(String login) {
        String sql = "SELECT * FROM users WHERE login=?";

        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), login);
    }

    @Override
    public User getUserByLoginAndPwd(String login, String password) {
        String sql = "SELECT * FROM users WHERE login=? AND password=?";

        return jdbcTemplate.query(sql, rs -> {
            if (rs.next()) {
                UserRowMapper userRowMapper = new UserRowMapper();
                return userRowMapper.mapRow(rs, 1);
            } else return null;
        }, login, password);
    }

    @Override
    public UserType getUserType(String login, String password) {
        String sql = "SELECT user_type FROM users WHERE login=? AND password=?";

        return jdbcTemplate.queryForObject(sql, UserType.class, login, password);
    }

    public static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();

            user.setUserType(UserType.valueOf(rs.getString("user_type")));
            user.setLogin(rs.getString("login"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setEmail(rs.getString("email"));
            user.setBirthday(rs.getDate("birthday").toLocalDate());
            user.setId(rs.getInt("id"));
            user.setPassword(rs.getString("password"));

            return user;
        }
    }
}
