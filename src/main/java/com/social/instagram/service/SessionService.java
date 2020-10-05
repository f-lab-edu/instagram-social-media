package com.social.instagram.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import static com.social.instagram.util.session.SessionKeyConstants.USER_ID;

/*
    SessionService 구성 이유
    객체지향적인 코드는 자신의 책임에 충실한 기능을 갖고 있으면 필요한 작업이 생기면 작업을 수행 해 달라고
    요청하는 것이다. 이를 바탕으로 코드를 보면 LoginController에게 세션을 생성하고 반환하는 것보다 LoginController에서
    SessionService에게 세션에 관한 작업을 위임하는 것이 바람직하다.
    그렇다면 layer별로 나누는 것이 어떠한 특징을 갖고 어떠한 장점이 살펴보겠다.

    layer 특징
    Controller layer
    요청에 해당하는 비즈니스 로직을 결정, 구성 요소간의 처리 흐름 제어, 데이터 조작 의뢰를 수행하는 역할
    "what to do"를 담당하는 계층

    business layer
    비즈니스 로직을 처리하고 트랜잭션 처리 제어 계층과 persistence 계층 사이를 연결하는 역할로 두 계층이 직접적으로 통신하지 않게
    하여 애플리케이션의 유연성을 증가시킨다.
    "how it's done"을 담당하는 계층

    persistence layer
    영구 데이터를 빼내어 객체화 시키며, 데이터베이스에 접근하여 데이터를 CRUD 작업 하는 계층

    layer별로 구성 하면 좋은 점
    1. 역할과 책임을 분명히 나눠 어디를 수정해야할지 명확하다.
    2. 단위테스트시 각각의 역할에만 테스트를 할 수 있어 작성하기 쉽다.
    3. 컴포넌트별로 구성되어 있어 재사용 할 수 있다.

    layer별로 구성하면 안 좋은 점
    1. 서비스가 확장함에 따라 설계가 복잡해지고 관리 포인트가 증가한다.
    2. 설계 시간이 증가한다.

    layer별로 구성하면 안 좋은 점들이 있지만 구성하는 이유는 서비스가 확장함에 따라 장점은 더 뚜렷해지기 때문이다.
    역할과 책임없는 코드가 흩어져서 사용되고, 불필요한 책임을 여러개 안고 있는 메소드를 테스트코드 작성한다고 하면
    복잡도는 증가하고 가독성이 떨어지게 된다.
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