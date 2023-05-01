package by.tms.eshopspringboot.repository;

import by.tms.eshopspringboot.model.User;
import by.tms.eshopspringboot.model.enums.UserType;

public interface UserRepository {
    void addUser(User user);

    User getUserByLogin(String login);

    User getUserByLoginAndPwd(String login, String password);

    UserType getUserType(String login, String password);
}
