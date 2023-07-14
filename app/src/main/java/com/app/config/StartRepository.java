package com.app.config;


import com.app.dtos.UserDTO;
import com.app.mappers.UserMapper;
import com.app.models.Token;
import com.app.models.User;
import com.app.repositories.TokenRepository;
import com.app.repositories.UserRepository;
import com.app.services.TokenService;
import com.app.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class StartRepository {

    /* Vars */

    private final UserService USER_SERVICE;

    private final TokenService TOKEN_SERVICE;

    private final Logger LOGGER;

    /* Constructor */

    public StartRepository(UserService userService, TokenService tokenService){
        USER_SERVICE = userService;
        TOKEN_SERVICE = tokenService;
        LOGGER = LoggerFactory.getLogger(StartRepository.class);
    }

    /* Event Listeners */

    @EventListener(ApplicationStartedEvent.class)
    public void _startMe(){

        UserDTO userDTO = new UserDTO("pablo", "pablo@email.com", "123");

        if(!USER_SERVICE._isUserExist(userDTO.email()))
            USER_SERVICE._createUser(userDTO);

        Token token = TOKEN_SERVICE._createTokenUser(userDTO.email());

        LOGGER.info("TOKEN: " + token.getId());
    }

}
