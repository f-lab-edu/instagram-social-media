package com.social.instagram.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/*
    @Getter
    getter 메소드를 자동으로 생성한다.
*/
@Getter
public class AccountDto {

    /*
            @NotBlank는 null, "", " "를 허용하지 않는다.
            그렇다면 @NotNull, @NotEmpty와 어떤 차이가 있을까?
            @NotNull  @NotEmpty  @NotBlank
        null  허용x       허용x       허용x
        ""    허용        허용x       허용x
        " "   허용        허용        허용x
    */
    @NotBlank(message = "아이디를 입력해주세요")
    private String userId;

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @NotBlank(message = "패스워드를 입력해주세요")
    private String password;

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    /*
        @Pattern
        해당 정규식을 만족할 경우에만 통과
    */
    @NotBlank(message = "핸드폰번호를 제대로 입력해주세요")
    @Pattern(regexp = "010-[0-9]{3,4}-[0-9]{4}", message = "010-1234-5678 양식으로 입력해주세요")
    private String phone;
}