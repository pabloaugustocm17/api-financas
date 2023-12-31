package com.app.repositories;

import com.app.models.User;
import com.app.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, UUID> {

    @Query(" SELECT W " +
            "FROM Wallet AS W " +
            "WHERE W.user_id.id = :id")
    Optional<Wallet> returnWalletByUserId(
            @Param("id") UUID id
    );

}
