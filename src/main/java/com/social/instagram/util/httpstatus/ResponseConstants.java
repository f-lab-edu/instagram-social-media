package com.social.instagram.util.httpstatus;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseConstants {

    public static final ResponseEntity<Void> RESPONSE_ENTITY_OK =
            ResponseEntity.ok().build();

    public static final ResponseEntity<Void> RESPONSE_ENTITY_CREATE =
            ResponseEntity.status(HttpStatus.CREATED).build();

    public static final ResponseEntity<String> RESPONSE_USER_ID_BAD_REQUEST =
            ResponseEntity.badRequest().body("중복된 userId 입니다");

    public static final ResponseEntity<String> RESPONSE_NOT_USER_DATA_BAD_REQUEST =
            ResponseEntity.badRequest().body("입력된 이메일과 일치하는 계정이 없습니다.");

}