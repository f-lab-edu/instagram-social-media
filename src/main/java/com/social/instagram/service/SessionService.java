package com.social.instagram.service;

import com.social.instagram.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import static com.social.instagram.util.session.SessionKeyConstants.ACCOUNT;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final HttpSession session;

    public void createAccountSession(Account account) {
        session.setAttribute(ACCOUNT, account);
    }

    public void removeAccountSession() {
        session.removeAttribute(ACCOUNT);
    }

}