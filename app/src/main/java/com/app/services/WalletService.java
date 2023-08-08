package com.app.services;

import com.app.exceptions.WalletException;
import com.app.mappers.WalletMapper;
import com.app.models.User;
import com.app.models.Wallet;
import com.app.repositories.WalletRepository;
import com.app.utils.ExceptionConsts;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Wallet _getWalletByUser(User user){

        Optional<Wallet> wallet = WALLET_REPOSITORY.returnWalletByUserId(user.getId());

        if(wallet.isEmpty())
            throw new WalletException(ExceptionConsts.WALLET_NO_EXIST);

        return wallet.get();
    }

    /* Utils */

    public void _verifyWalletUser(User user){

        Optional<Wallet> wallet = WALLET_REPOSITORY.returnWalletByUserId(user.getId());

        if(wallet.isPresent())
            throw new WalletException(ExceptionConsts.USER_HAVE_WALLET);

    }
}
