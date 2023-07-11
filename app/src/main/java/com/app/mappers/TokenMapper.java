package com.app.mappers;

import com.app.models.Token;

public class TokenMapper {

    public static Token _createTokenByEmail(String email){
        return new Token(email);
    }

}
