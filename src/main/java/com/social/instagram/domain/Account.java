package com.social.instagram.domain;

import com.social.instagram.dto.AccountDto;
import com.social.instagram.util.PasswordEncodingUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
    @Entity
    JPA를 사용하여 테이블과 매핑 할 클래스이며 주의해야 할점은 기본생성자 필수이며 final, enum, interface에는 사용불가
    @NoArgsConstructor
    기본 생성자를 자동으로 생성
*/
@Entity
@NoArgsConstructor
@Getter
public class Account {

    /*
        @Id
        Entity의 primary key를 명시해준다
        @GeneratedValue
        기본키의 생성 전략을 결정해준다. 기본값은 Auto이며 IDENTITY의 전략은 기본키 생성을 데이터베이스 위임하는 방법이다.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userId;

    private String name;

    private String password;

    private String email;

    private String phone;

    @Builder
    public Account(String userId, String name, String password, String email, String phone) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public static Account changeAccountEntity(AccountDto account) {
        return Account.builder()
                .userId(account.getUserId())
                .name(account.getName())
                .password(PasswordEncodingUtil.changePasswordEncoding(account.getPassword()))
                .email(account.getEmail())
                .phone(account.getPhone())
                .build();
    }

}