package by.tms.eshopspringboot.entity.mapper;

import by.tms.eshopspringboot.dto.UserDTO;
import by.tms.eshopspringboot.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public User toUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setRoles(userDTO.getRoles());
        user.setBirthday(userDTO.getBirthday());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return user;
    }
}
