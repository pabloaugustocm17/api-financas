package com.app.controllers;

import com.app.dtos.LoginDTO;
import com.app.dtos.UserDTO;
import com.app.models.User;
import com.app.responses.ErrorResponse;
import com.app.responses.SuccessResponse;
import com.app.services.UserService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/login/")
public class LoginController {

    /* Vars */

    private final UserService USER_SERVICE;

    /* Constructor */

    public LoginController(UserService userService){
        this.USER_SERVICE = userService;
    }

    /* Posts */

    @PostMapping
    @RateLimiter(name = "login", fallbackMethod = "_fallbackLogin")
    public ResponseEntity<?> _login(
            @RequestBody LoginDTO login
    ){

        User user = USER_SERVICE._realizeLogin(login);

        return SuccessResponse._success(user._getId());

    }

    /* Fallback */

    public ResponseEntity<?> _fallbackLogin(LoginDTO login_dto, Exception e){
        return ErrorResponse._unauthorized(e.getMessage());
    }
}
