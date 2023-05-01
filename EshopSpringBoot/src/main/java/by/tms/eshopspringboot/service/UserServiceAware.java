package by.tms.eshopspringboot.service;

import by.tms.eshopspringboot.model.User;

public interface UserServiceAware {
    void addUser(User user);

    User getUserByLogin(String login);

    boolean loginInUse(String login);

    boolean validateUser(String login, String password);
}
