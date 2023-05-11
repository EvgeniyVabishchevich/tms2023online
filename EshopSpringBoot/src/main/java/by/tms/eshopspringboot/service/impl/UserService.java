package by.tms.eshopspringboot.service.impl;

import by.tms.eshopspringboot.entity.User;
import by.tms.eshopspringboot.repository.impl.UserRepositoryImpl;
import by.tms.eshopspringboot.service.UserServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceAware {
    private final UserRepositoryImpl userRepositoryImpl;

    @Override
    public void addUser(User user) {
        userRepositoryImpl.save(user);
    }

    @Override
    public User findByLogin(String login) {
        return userRepositoryImpl.findByLogin(login);
    }

    @Override
    public boolean existsByLoginAndPassword(String login, String password) {
        return userRepositoryImpl.existsByLoginAndPassword(login, password);
    }
}
