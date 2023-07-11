package com.app.controllers;

import com.app.dtos.WalletDTO;
import com.app.models.User;
import com.app.responses.ErrorResponse;
import com.app.responses.SuccessResponse;
import com.app.services.UserService;
import com.app.services.WalletService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    @RateLimiter(name = "create-wallet", fallbackMethod = "_fallbackCreateWallet")
    public ResponseEntity<?> _createWallet(
            @RequestBody WalletDTO walletDTO
    ){

        User user = USER_SERVICE._findUserByUUID(walletDTO.id_user());

        UUID id_wallet = WALLET_SERVICE._createWallet(user);

        return SuccessResponse._success(id_wallet);

    }

    /* Fallbacks */

    public ResponseEntity<?> _fallbackCreateWallet(WalletDTO walletDTO, Exception e){
        return ErrorResponse._forbidden(e.getMessage());
    }
}
