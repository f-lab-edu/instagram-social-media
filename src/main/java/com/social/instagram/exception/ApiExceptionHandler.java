package com.social.instagram.exception;

import com.social.instagram.validation.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.social.instagram.util.httpstatus.ResponseConstants.RESPONSE_USER_ID_BAD_REQUEST;
import static com.social.instagram.util.httpstatus.ResponseConstants.RESPONSE_USER_ACCOUNT_BAD_REQUEST;
import static com.social.instagram.util.httpstatus.ResponseConstants.RESPONSE_USER_UNAUTHORIZED;

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
    @ExceptionHandler(UserIdDuplicatedException.class)
    public ResponseEntity<String> handleDuplicateUserIdException() {
        return RESPONSE_USER_ID_BAD_REQUEST;
    }

    @ExceptionHandler(UserNotAccountException.class)
    public ResponseEntity<String> handleNotUserDataException() {
        return RESPONSE_USER_ACCOUNT_BAD_REQUEST;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getBindingResult().getFieldErrors());
        return new ResponseEntity<>(errorMessage.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotLoginException.class)
    public ResponseEntity<String> handleSessionNotFoundException() {
        return RESPONSE_USER_UNAUTHORIZED;
    }

}