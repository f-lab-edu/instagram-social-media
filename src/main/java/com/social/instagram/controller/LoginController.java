package com.social.instagram.controller;

import com.social.instagram.dto.LoginDto;
import com.social.instagram.factory.EncryptionFactory;
import com.social.instagram.service.AccountService;
import com.social.instagram.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.social.instagram.util.httpstatus.ResponseConstants.RESPONSE_ENTITY_OK;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final EncryptionFactory encryptionFactory;
    private final LoginService loginService;
    private final AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginDto loginDto) {
        String userId = loginDto.getUserId();
        String password = encryptionFactory.sha256Encryption().changeEncoding(loginDto.getPassword());

        accountService.validateHasAccount(userId, password);

        loginService.createUserId(userId);

        return RESPONSE_ENTITY_OK;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        loginService.removeUserId();

        return RESPONSE_ENTITY_OK;
    }

}