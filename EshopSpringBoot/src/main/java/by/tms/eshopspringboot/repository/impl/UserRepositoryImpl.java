package by.tms.eshopspringboot.repository.impl;

import by.tms.eshopspringboot.model.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryImpl extends ListCrudRepository<User, Integer> {
    User findByLogin(String login);

    boolean existsByLoginAndPassword(String login, String password);
}
