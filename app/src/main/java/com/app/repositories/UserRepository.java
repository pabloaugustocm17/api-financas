package com.app.repositories;

import com.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    
    Optional<User> findUserByEmail(String email);

    @Query( "SELECT U " +
            "FROM User U " +
            "WHERE U.email = :email " +
            "AND U.password = :password")
    Optional<User> _realizeLogin(
            String email,
            String password
    );

}
