package com.app.services;

import com.app.dtos.LoginDTO;
import com.app.dtos.UserDTO;
import com.app.exceptions.UserException;
import com.app.mappers.UserMapper;
import com.app.models.User;
import com.app.repositories.UserRepository;
import com.app.utils.ExceptionConsts;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    /* Vars */

    private final UserRepository USER_REPOSITORY;

    /* Constructor */

    public UserService(UserRepository userRepository){
        this.USER_REPOSITORY = userRepository;
    }

    /* JPA Comunication */

    public UUID _createUser(UserDTO user_dto){

        this._verifyUser(user_dto.email());

        User user_save = UserMapper._createUserByDTO(user_dto);

        USER_REPOSITORY.save(user_save);

        return user_save._getId();

    }

    public User _realizeLogin(LoginDTO login){

        Optional<User> user = USER_REPOSITORY._realizeLogin(login.email(), login.password());

        this._verifyLogin(user);

        return user.get();

    }

    /* Utils */

    private void _verifyLogin(Optional<User> user){

        if(user.isEmpty()){
            throw new UserException(ExceptionConsts.LOGIN_ERROR);
        }

    }

    private void _verifyUser(String email){

        Optional<User> user = USER_REPOSITORY.findUserByEmail(email);

        if(user.isPresent()){
            throw new UserException(ExceptionConsts.USER_ALREADY_EXIST);
        }
    }

}
