package by.tms.eshopspringboot.repository;

import by.tms.eshopspringboot.entity.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {
    Optional<User> findByLogin(String login);

    boolean existsByLoginAndPassword(String login, String password);
}
