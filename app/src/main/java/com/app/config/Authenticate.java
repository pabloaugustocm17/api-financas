package com.app.config;

import com.app.models.Token;
import com.app.repositories.TokenRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;


@Configuration
public class Authenticate implements Filter {

    /* Vars */

    private final TokenRepository TOKEN_REPOSITORY;

    /* Constructor */

    public Authenticate(TokenRepository tokenRepository){
        this.TOKEN_REPOSITORY = tokenRepository;
    }

    /* Implements */

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String authroization = request.getHeader("Authorization");

        if(!request.getRequestURI().contains("login")){

            if(authroization == null){
                throw new ServletException("Unsecure Request");
            }

            _doAuthenticate(authroization);
        }

        filterChain.doFilter(request, response);
    }

    /* Methods */

    public void _doAuthenticate(String id_authenticate) throws ServletException {

        UUID id = UUID.fromString(id_authenticate);

        Optional<Token> token = TOKEN_REPOSITORY.findById(id);

        if(token.isEmpty())
            throw new ServletException("No Authorization");
    }
}
