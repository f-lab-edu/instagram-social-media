package com.social.instagram.service;

import com.social.instagram.domain.Account;
import com.social.instagram.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void accountRegister(Account account) {
        checkDuplicateUserId(account.getUserId());

        accountRepository.save(account);
    }

    public boolean checkDuplicateUserId(String userId) {
        return accountRepository.existsByUserId(userId);
    }

}