package com.app.mappers;

import com.app.dtos.UserDTO;
import com.app.models.User;

public class UserMapper {

    public static User _createUserByDTO(UserDTO userDTO){

        return new User(
                userDTO.name(),
                userDTO.email(),
                userDTO.password()
        );
    }
}
