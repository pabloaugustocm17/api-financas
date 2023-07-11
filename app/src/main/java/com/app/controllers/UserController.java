package com.app.controllers;

import com.app.dtos.UserDTO;
import com.app.responses.ErrorResponse;
import com.app.responses.SuccessResponse;
import com.app.services.UserService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {

    /* Vars */

    private final UserService USER_SERVICE;

    /* Constructor */

    public UserController(UserService userService){
        this.USER_SERVICE = userService;
    }

    /* Posts */

    @PostMapping
    @RateLimiter(name = "create-user", fallbackMethod = "_fallbackCreateUser")
    public ResponseEntity<?> _createUser(
            @RequestBody UserDTO user_dto
    ){

        UUID id = USER_SERVICE._createUser(user_dto);

        return SuccessResponse._success(id);
    }

    /* Fallbacks */

    public ResponseEntity<?> _fallbackCreateUser(UserDTO user_dto, Exception e){
        return ErrorResponse._forbidden(e.getMessage());
    }

}
