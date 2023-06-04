package by.tms.eshopspringboot.service.impl;

import by.tms.eshopspringboot.dto.UserDTO;
import by.tms.eshopspringboot.dto.mapper.UserDTOMapper;
import by.tms.eshopspringboot.entity.mapper.UserMapper;
import by.tms.eshopspringboot.exception.NotFoundException;
import by.tms.eshopspringboot.repository.UserRepository;
import by.tms.eshopspringboot.service.UserServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceAware {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserDTOMapper userDTOMapper;

    @Override
    public void addUser(UserDTO userDTO) {
        userRepository.save(userMapper.toUser(userDTO));
    }

    @Override
    public UserDTO findByLogin(String login) throws NotFoundException {
        return userDTOMapper.toDTO(userRepository.findByLogin(login).orElseThrow(
                () -> new NotFoundException(String.format("Cannot find user by login = %s", login))));
    }
}

