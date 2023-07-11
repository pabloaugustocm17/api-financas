package com.app.controllers;

import com.app.dtos.UserDTO;
import com.app.models.User;
import com.app.responses.ErrorResponse;
import com.app.responses.SuccessResponse;
import com.app.services.TokenService;
import com.app.services.UserService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {

    /* Vars */

    private final UserService USER_SERVICE;

    private final TokenService TOKEN_SERVICE;

    /* Constructor */

    public UserController(UserService userService, TokenService tokenService){
        this.USER_SERVICE = userService;
        this.TOKEN_SERVICE = tokenService;
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

    /* Gets */

    @GetMapping
    @RateLimiter(name = "get-user", fallbackMethod = "_fallbackGetUser")
    public ResponseEntity<?> _getUser(
            @RequestHeader("Authorization") String authorization
    ){

        UUID token = UUID.fromString(authorization);

        User user = TOKEN_SERVICE._getUserByToken(token);

        return SuccessResponse._success(user);
    }

    /* Fallbacks */

    public ResponseEntity<?> _fallbackCreateUser(UserDTO user_dto, Exception e){
        return ErrorResponse._forbidden(e.getMessage());
    }

    public ResponseEntity<?> _fallbackGetUser(String authorization, Exception e){
        return ErrorResponse._forbidden(e.getMessage());
    }

}
