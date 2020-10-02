package com.social.instagram.service;

import com.social.instagram.domain.Account;
import com.social.instagram.exception.DuplicateUserIdException;
import com.social.instagram.exception.NotUserDataException;
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

    public void checkDuplicateUserId(String userId) {
        if (accountRepository.existsByUserId(userId)) {
            throw new DuplicateUserIdException();
        }
    }

    public void hasAccount(String userId, String password) {
        if (!accountRepository.existsFindByUserIdAndPassword(userId, password)) {
            throw new NotUserDataException();
        }
    }

    public Account getAccount(String userId) {
        return accountRepository.findByUserId(userId);
    }

}