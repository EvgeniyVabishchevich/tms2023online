package by.tms.eshopspringboot.service;

import by.tms.eshopspringboot.dto.UserDTO;
import by.tms.eshopspringboot.exception.NotFoundException;

public interface UserServiceAware {
    void addUser(UserDTO userDTO);

    UserDTO findByLogin(String login) throws NotFoundException;
}
