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

    public static final ResponseEntity<String> RESPONSE_USER_ACCOUNT_BAD_REQUEST =
            ResponseEntity.badRequest().body("입력된 이메일과 일치하는 계정이 없습니다");

    public static final ResponseEntity<String> RESPONSE_USER_UNAUTHORIZED =
            new ResponseEntity<>("유저에 대한 정보를 찾지 못했습니다. 로그인을 해주세요", HttpStatus.UNAUTHORIZED);

    public static final ResponseEntity<String> RESPONSE_AWS_S3_FILE_NOT_UPLOAD =
            new ResponseEntity<>("문제가 발생하여 다시 요청을 보내주세요", HttpStatus.INTERNAL_SERVER_ERROR);

    public static final ResponseEntity<String> RESPONSE_FOLLOW_NOT_DELETE =
            new ResponseEntity<>("팔로우 취소가 실패 했습니다", HttpStatus.INTERNAL_SERVER_ERROR);

    public static final ResponseEntity<String> RESPONSE_AWS_S3_FILE_FAIL_ACCESS =
            new ResponseEntity<>("S3 업로드 작업시 파일에 대한 엑세스가 실패 했습니다",
                    HttpStatus.INTERNAL_SERVER_ERROR);
}