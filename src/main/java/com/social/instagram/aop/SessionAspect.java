package com.social.instagram.aop;

import com.social.instagram.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
@RequiredArgsConstructor
public class SessionAspect {

    private final SessionService sessionService;

    @Before("@annotation(com.social.instagram.annotation.SessionHasCheck)")
    public void validateSession() {
        sessionService.validateHasSession();
    }

}