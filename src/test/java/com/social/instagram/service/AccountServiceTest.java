package com.social.instagram.service;

import com.social.instagram.domain.Account;
import com.social.instagram.exception.UserIdDuplicatedException;
import com.social.instagram.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private Account account;

    @BeforeEach
    public void setUp() {
        this.account = createAccount();
    }

    @Test
    @DisplayName("회원가입을 성공적으로 수행한다")
    public void account_register_success() {
        accountService.accountRegister(account);

        verify(accountRepository).save(account);
    }

    @Test
    @DisplayName("회원가입 할 때 중복된 아이디가 있으면 실패한다.")
    public void account_register_duplicate_userId_fail() {
        when(accountRepository.existsByUserId("test")).thenReturn(true);

        assertThrows(UserIdDuplicatedException.class,
                () -> accountService.validateDuplicateUserId("test"));

        verify(accountRepository).existsByUserId("test");
    }

    private Account createAccount() {
        return Account.builder()
                .userId("abc@naver.com")
                .name("test")
                .password("qweoajasdkjansd1298")
                .email("abc@hanmail.net")
                .phone("010-1234-5678")
                .build();
    }

}