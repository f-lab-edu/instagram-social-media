package com.social.instagram.controller;

import com.social.instagram.dto.LoginDto;
import com.social.instagram.factory.EncryptionFactory;
import com.social.instagram.service.AccountService;
import com.social.instagram.service.SessionService;
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
    private final SessionService sessionService;
    private final AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<Void> accountLogin(@Valid @RequestBody LoginDto loginDto) {
        String userId = loginDto.getUserId();
        String password = encryptionFactory.sha256Encryption().changeEncoding(loginDto.getPassword());

        accountService.validateHasAccount(userId, password);

        sessionService.createUserIdSession(userId);

        return RESPONSE_ENTITY_OK;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> accountLogout() {
        sessionService.removeAccountSession();

        return RESPONSE_ENTITY_OK;
    }

}