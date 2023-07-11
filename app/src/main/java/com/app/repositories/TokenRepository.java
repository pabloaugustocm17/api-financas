package com.app.repositories;

import com.app.models.Token;
import com.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {

    Optional<Token> findTokenByEmail(String email);

    @Query( "SELECT U " +
            "FROM Token T " +
            "INNER JOIN User U " +
            "ON U.email = T.email " +
            "WHERE T.id = :token")
    Optional<User> _findUserByToken(UUID token);

}
