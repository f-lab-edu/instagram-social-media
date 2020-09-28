package com.social.instagram.util.httpstatus;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseContents {

    public static final ResponseEntity<Void> RESPONSE_ENTITY =
            ResponseEntity.status(HttpStatus.CREATED).build();

}