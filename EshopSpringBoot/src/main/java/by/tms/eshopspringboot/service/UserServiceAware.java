package by.tms.eshopspringboot.service;

import by.tms.eshopspringboot.model.User;

public interface UserServiceAware {
    void addUser(User user);

    User findByLogin(String login);

    boolean existsByLoginAndPassword(String login, String password);
}
