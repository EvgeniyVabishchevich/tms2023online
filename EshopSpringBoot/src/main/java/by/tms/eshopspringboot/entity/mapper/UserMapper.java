package by.tms.eshopspringboot.entity.mapper;

import by.tms.eshopspringboot.dto.UserDTO;
import by.tms.eshopspringboot.entity.User;
import by.tms.eshopspringboot.entity.mapper.password.EncodedMapping;
import by.tms.eshopspringboot.entity.mapper.password.PasswordEncoderMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PasswordEncoderMapper.class)
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    @Mapping(source = "password", target = "password", qualifiedBy = EncodedMapping.class)
    User userDTOToUser(UserDTO userDTO);
}
