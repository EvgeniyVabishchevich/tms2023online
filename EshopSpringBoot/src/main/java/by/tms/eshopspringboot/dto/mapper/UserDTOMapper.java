package by.tms.eshopspringboot.dto.mapper;

import by.tms.eshopspringboot.dto.UserDTO;
import by.tms.eshopspringboot.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }
}
