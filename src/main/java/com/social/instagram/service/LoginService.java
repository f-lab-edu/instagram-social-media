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

    Session을 캡슐화하여 필요한 기능만 제공하는 것은 객체지향 특징 중 하나다. 그렇다면 왜 캡슐화 해야 할까?
    캡슐화하지 않은 코드는 어디서든 접근 가능한데 이 말은 중요한 기능을 가진 코드도 어디서든 접근 가능하다. 중요한 기능을 숨기는게
    왜 중요할까? 예시를 들어보겠다.
    계좌 조회 하는 기능에 개발자의 실수로 계좌 이체의 기능을 넣는다면 돈이 빠져나가는 심각한 문제가 발생한다. 이러한 문제를 방지하고자
    중요한 기능을 숨기고 외부에서 접근 하지 못하도록 하는 것이 바람직하다.
*/
@Service
@RequiredArgsConstructor
public class LoginService {

    private final HttpSession session;

    public void createUserId(String userId) {
        session.setAttribute(USER_ID, userId);
    }

    public void removeUserId() {
        session.removeAttribute(USER_ID);
    }

    public String getUserId() {
        return (String) session.getAttribute(USER_ID);
    }

}