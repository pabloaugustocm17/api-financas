package com.app.services;

import com.app.mappers.TokenMapper;
import com.app.models.Token;
import com.app.repositories.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenService {

    /* Vars */

    private final TokenRepository TOKEN_REPOSITORY;

    /* Constructor */

    public TokenService(TokenRepository tokenRepository){
        this.TOKEN_REPOSITORY = tokenRepository;
    }

    /* JPA Comunication */

    public Token _createTokenUser(String email){

        Token token = _verifyToken(email);

        TOKEN_REPOSITORY.save(token);

        return token;

    }

    /* Utils */

    private Token _verifyToken(String email_user){

        Optional<Token> token = TOKEN_REPOSITORY.findTokenByEmail(email_user);

        token.ifPresent(TOKEN_REPOSITORY::delete);

        return TokenMapper._createTokenByEmail(email_user);

    }

}
