package com.social.instagram.util.httpstatus;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseConstants {

    public static final ResponseEntity<Void> RESPONSE_ENTITY_CREATE =
            ResponseEntity.status(HttpStatus.CREATED).build();

    public static final ResponseEntity<String> RESPONSE_USER_ID_BAD_REQUEST =
            ResponseEntity.badRequest().body("중복된 userId 입니다");

}