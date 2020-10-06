package com.social.instagram.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import static com.social.instagram.util.session.SessionKeyConstants.USER_ID;

/*
    SessionService
    객체지향적인 코드는 자신의 책임에 충실한 기능을 갖고 있으면 필요한 작업이 생기면 작업을 수행 해 달라고
    요청하는 것이다. 이를 바탕으로 코드를 보면 LoginController에게 세션을 생성하고 반환하는 것보다 LoginController에서
    SessionService에게 세션에 관한 작업을 위임하는 것이 바람직하다.
*/
@Service
@RequiredArgsConstructor
public class SessionService {

    private final HttpSession session;

    public void createUserIdSession(String userId) {
        session.setAttribute(USER_ID, userId);
    }

    public void removeAccountSession() {
        session.removeAttribute(USER_ID);
    }

}