package com.social.instagram.aop;

import com.social.instagram.exception.SessionNotFoundException;
import com.social.instagram.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

/*
    SessionAspect
    유저가 로그인을 하게 되면 세션이 생성된다. 생성 이후 세션이 존재하는지 유효성을 매번 검사해야 하는데
    각 로직마다 중복된 코드로 실행되고 이는 횡단 관심에 해당한다. 이러한 이유로 aop를 적용하여 로직마다 중복된 코드를
    방지하고 비즈니스 관심사와 횡단 관심사를 분리하여 비즈니스 로직에 더 집중할 수 있다.
*/
@Configuration
@Aspect
@RequiredArgsConstructor
public class SessionAspect {

    private final SessionService sessionService;

    @Before("@annotation(com.social.instagram.annotation.SessionValidation)")
    public void validateUserIdExpiration() {
        Optional.ofNullable(sessionService.getUserId())
                .orElseThrow(SessionNotFoundException::new);
    }

}