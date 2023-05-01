package by.tms.eshopspringboot.service.impl;

import by.tms.eshopspringboot.model.User;
import by.tms.eshopspringboot.repository.UserRepository;
import by.tms.eshopspringboot.service.UserServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceAware {
    private final UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.addUser(user);
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

    @Override
    public boolean loginInUse(String login) {
        return userRepository.getUserByLogin(login) != null;
    }

    @Override
    public boolean validateUser(String login, String password) {
        return userRepository.getUserByLoginAndPwd(login, password) != null;
    }
}
