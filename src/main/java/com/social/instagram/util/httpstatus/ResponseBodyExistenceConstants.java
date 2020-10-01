package com.social.instagram.util.httpstatus;

import org.springframework.http.ResponseEntity;

public class ResponseBodyExistenceConstants {

    public static final ResponseEntity<String> RESPONSE_USER_ID_BAD_REQUEST =
            ResponseEntity.badRequest().body("중복된 userId 입니다");

}