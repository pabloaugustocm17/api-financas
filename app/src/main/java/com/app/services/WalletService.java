package com.app.services;

import com.app.exceptions.WalletException;
import com.app.mappers.WalletMapper;
import com.app.models.User;
import com.app.models.Wallet;
import com.app.repositories.WalletRepository;
import com.app.utils.ExceptionConsts;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class WalletService {

    /* Vars */

    private final WalletRepository WALLET_REPOSITORY;

    /* Constructor */

    public WalletService(WalletRepository walletRepository){
        this.WALLET_REPOSITORY = walletRepository;
    }

    /* JPA Communication */

    public UUID _createWallet(User user){

        _verifyWalletUser(user);

        Wallet wallet = WalletMapper._createWalletByUser(user);

        WALLET_REPOSITORY.save(wallet);

        return wallet.getId();

    }

    /* Utils */

    public void _verifyWalletUser(User user){

        Optional<Wallet> wallet = WALLET_REPOSITORY.findWalletByUser(user);

        if(wallet.isPresent())
            throw new WalletException(ExceptionConsts.USER_HAVE_WALLET);

    }
}
