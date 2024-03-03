package com.users.mapper;

import com.users.dto.UserDTO;
import com.users.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDTO toDto(User entity);

    User fromDto(UserDTO dto);
}
