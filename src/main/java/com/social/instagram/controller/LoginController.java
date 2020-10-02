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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.social.instagram.util.httpstatus.ResponseSuccessContents.RESPONSE_ENTITY_OK;
import static com.social.instagram.util.session.SessionNameConstants.ACCOUNT;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final EncryptionFactory encryptionFactory;
    private final SessionService sessionService;
    private final AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<Void> accountLogin(@Valid @RequestBody LoginDto loginDto, HttpSession session) {
        String userId = loginDto.getUserId();
        String password = encryptionFactory.sha256Util().changeEncoding(loginDto.getPassword());

        accountService.hasAccount(userId, password);

        session.setAttribute(ACCOUNT, sessionService.getAccount(userId));

        return RESPONSE_ENTITY_OK;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> accountLogout(HttpSession session) {
        session.removeAttribute(ACCOUNT);

        return RESPONSE_ENTITY_OK;
    }

}