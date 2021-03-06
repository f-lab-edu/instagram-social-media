package com.social.instagram.service;

import com.social.instagram.domain.Account;
import com.social.instagram.exception.UserIdDuplicatedException;
import com.social.instagram.exception.UserNotAccountException;
import com.social.instagram.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void accountRegister(Account account) {
        validateDuplicateUserId(account.getUserId());

        accountRepository.save(account);
    }

    public void validateDuplicateUserId(String userId) {
        if (accountRepository.existsByUserId(userId)) {
            throw new UserIdDuplicatedException();
        }
    }

    public void validateHasAccount(String userId, String password) {
        if (!accountRepository.existsFindByUserIdAndPassword(userId, password)) {
            throw new UserNotAccountException();
        }
    }
  
}