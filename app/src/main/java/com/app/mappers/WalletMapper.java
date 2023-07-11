package com.app.mappers;

import com.app.models.User;
import com.app.models.Wallet;

public class WalletMapper {

    public static Wallet _createWalletByUser(User user){

        return new Wallet(user);

    }

}
