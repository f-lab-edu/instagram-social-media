package com.social.instagram.service;

import com.social.instagram.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final AccountService accountService;

    public Account getAccount(String userId) {
        return accountService.getAccount(userId);
    }

}