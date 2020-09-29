package com.social.instagram.util.httpstatus;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseSuccessContents {

    public static final ResponseEntity<Void> RESPONSE_ENTITY_CREATE =
            ResponseEntity.status(HttpStatus.CREATED).build();

    public static final ResponseEntity<Void> RESPONSE_ENTITY_OK =
            ResponseEntity.ok().build();

}