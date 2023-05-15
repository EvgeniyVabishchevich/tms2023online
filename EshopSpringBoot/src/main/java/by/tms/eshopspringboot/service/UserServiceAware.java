package by.tms.eshopspringboot.service;

import by.tms.eshopspringboot.entity.User;
import exception.NotFoundException;

public interface UserServiceAware {
    void addUser(User user);

    User findByLogin(String login) throws NotFoundException;

    boolean existsByLoginAndPassword(String login, String password);
}
