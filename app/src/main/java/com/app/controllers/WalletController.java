package com.app.controllers;

import com.app.models.User;
import com.app.models.Wallet;
import com.app.responses.ErrorResponse;
import com.app.responses.SuccessResponse;
import com.app.services.UserService;
import com.app.services.WalletService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/wallet/")
public class WalletController {

    /* Vars */

    private final WalletService WALLET_SERVICE;

    private final UserService USER_SERVICE;

    /* Constructor */

    public WalletController(WalletService walletService, UserService userService){
        this.WALLET_SERVICE = walletService;
        this.USER_SERVICE = userService;
    }

    /* Posts */

    @PostMapping("{id_user}")
    @RateLimiter(name = "create-wallet", fallbackMethod = "_fallbackCreateWallet")
    public ResponseEntity<?> _createWallet(
            @PathVariable String id_user
    ){

        User user = USER_SERVICE._findUserByUUID(UUID.fromString(id_user));

        UUID id_wallet = WALLET_SERVICE._createWallet(user);

        return SuccessResponse._success(id_wallet);

    }

    @GetMapping("/{id_user}")
    public ResponseEntity<?> _getWalletsUser(
            @PathVariable String id_user
    ){

        User user = USER_SERVICE._findUserByUUID(UUID.fromString(id_user));

        Wallet wallet = WALLET_SERVICE._getWalletByUser(user);

        return SuccessResponse._success(wallet);

    }

    /* Fallbacks */

    public ResponseEntity<?> _fallbackCreateWallet(String id_user, Exception e){
        return ErrorResponse._forbidden(e.getMessage());
    }
}
