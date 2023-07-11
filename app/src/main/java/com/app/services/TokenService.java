package com.app.services;

import com.app.exceptions.UserException;
import com.app.mappers.TokenMapper;
import com.app.models.Token;
import com.app.models.User;
import com.app.repositories.TokenRepository;
import com.app.utils.ExceptionConsts;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TokenService {

    /* Vars */

    private final TokenRepository TOKEN_REPOSITORY;

    /* Constructor */

    public TokenService(TokenRepository tokenRepository){
        this.TOKEN_REPOSITORY = tokenRepository;
    }

    /* JPA Communication */

    public Token _createTokenUser(String email){

        Token token = _verifyToken(email);

        TOKEN_REPOSITORY.save(token);

        return token;

    }

    public User _getUserByToken(UUID token){

        Optional<User> user = TOKEN_REPOSITORY._findUserByToken(token);

        if(user.isEmpty())
            throw new UserException(ExceptionConsts.USER_NO_EXIST);

        return user.get();

    }

    /* Utils */

    private Token _verifyToken(String email_user){

        Optional<Token> token = TOKEN_REPOSITORY.findTokenByEmail(email_user);

        token.ifPresent(TOKEN_REPOSITORY::delete);

        return TokenMapper._createTokenByEmail(email_user);

    }

}
