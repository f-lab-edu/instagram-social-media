package com.social.instagram.controller;

import com.social.instagram.dto.LoginDto;
import com.social.instagram.factory.EncryptionFactory;
import com.social.instagram.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.social.instagram.util.httpstatus.ResponseSuccessContents.RESPONSE_ENTITY_OK;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final EncryptionFactory encryptionFactory;

    @PostMapping("/login")
    public ResponseEntity<Void> accountLogin(@Valid @RequestBody LoginDto loginDto, HttpSession session) {
        String userId = loginDto.getUserId();
        String password = encryptionFactory.sha256Util().changeEncoding(loginDto.getPassword());

        loginService.hasAccount(userId, password);

        session.setAttribute("account", loginService.getAccount(userId));

        return RESPONSE_ENTITY_OK;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> accountLogout(HttpSession session) {
        session.removeAttribute("account");

        return RESPONSE_ENTITY_OK;
    }

}