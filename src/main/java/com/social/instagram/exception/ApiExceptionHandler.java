package com.social.instagram.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import static com.social.instagram.util.httpstatus.ResponseBodyExistenceConstants.RESPONSE_USER_ID_BAD_REQUEST;

/*
    @ControllerAdvice
    컨트롤러에서 발생하는 예외를 잡아서 처리해준다
*/
@ControllerAdvice
public class ApiExceptionHandler {

    /*
        @ExceptionHandler
        컨트롤러에서 정의한 메소드에서 기술한 예외가 발생하면 자동으로 받아 낼 수 있다.
    */
    @ExceptionHandler(DuplicateUserIdException.class)
    public ResponseEntity<String> handleDuplicateUserIdException() {
        return RESPONSE_USER_ID_BAD_REQUEST;
    }

}