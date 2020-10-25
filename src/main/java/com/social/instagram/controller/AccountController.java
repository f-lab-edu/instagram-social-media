package com.social.instagram.controller;

import com.social.instagram.domain.Account;
import com.social.instagram.dto.AccountDto;
import com.social.instagram.factory.EncryptionFactory;
import com.social.instagram.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import static com.social.instagram.util.httpstatus.ResponseConstants.RESPONSE_ENTITY_CREATE;



/*
    @RestController
        @Controller + @ResponseBody로 구성되어 있으며 Json 형태로 객체 데이터를 반환합니다.
    @RequiredArgsConstructor
        초기화 되지 않은 final 필드, @NonNull 어노테이션이 붙은 필드에 대한 생성자 생성
    @RequestMapping
        DefaultAnnotationHandlerMapping에서 컨트롤러를 선택할 때 대표적으로 사용하는 어노테이션
*/
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts/")
public class AccountController {

    private final AccountService accountService;
    private final EncryptionFactory encryptionFactory;

    @PostMapping("sign-up")
    public ResponseEntity<Void> registerAccount(@Valid @RequestBody AccountDto account) {
        accountService.accountRegister(Account.changeAccountEntity(account, encryptionFactory.sha256Encryption()));

        return RESPONSE_ENTITY_CREATE;
    }

}