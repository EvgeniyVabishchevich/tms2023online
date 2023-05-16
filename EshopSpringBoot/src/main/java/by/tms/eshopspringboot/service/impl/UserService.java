package by.tms.eshopspringboot.service.impl;

import by.tms.eshopspringboot.entity.User;
import by.tms.eshopspringboot.repository.UserRepository;
import by.tms.eshopspringboot.service.UserServiceAware;
import exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceAware {
    private final UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByLogin(String login) throws NotFoundException {
        return userRepository.findByLogin(login).orElseThrow(
                () -> new NotFoundException(String.format("Cannot find user by login = %s", login)));
    }

    @Override
    public boolean existsByLoginAndPassword(String login, String password) {
        return userRepository.existsByLoginAndPassword(login, password);
    }
}
