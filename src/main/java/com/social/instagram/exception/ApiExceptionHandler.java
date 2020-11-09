package com.social.instagram.exception;

import com.social.instagram.validation.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.social.instagram.util.httpstatus.ResponseConstants.RESPONSE_USER_ID_BAD_REQUEST;
import static com.social.instagram.util.httpstatus.ResponseConstants.RESPONSE_USER_ACCOUNT_BAD_REQUEST;
import static com.social.instagram.util.httpstatus.ResponseConstants.RESPONSE_USER_UNAUTHORIZED;
import static com.social.instagram.util.httpstatus.ResponseConstants.RESPONSE_AWS_S3_FILE_NOT_UPLOAD;

/*
    @ControllerAdvice
    컨트롤러에서 발생하는 예외를 잡아서 처리해준다
*/
@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

    /*
        @ExceptionHandler
        컨트롤러에서 정의한 메소드에서 기술한 예외가 발생하면 자동으로 받아 낼 수 있다.
    */
    @ExceptionHandler(UserIdDuplicatedException.class)
    public ResponseEntity<String> handleDuplicateUserIdException(UserIdDuplicatedException exception) {
        log.error("[{}] {}", "중복된 아이디가 존재합니다.", ExceptionUtils.getStackTrace(exception));
        return RESPONSE_USER_ID_BAD_REQUEST;
    }

    @ExceptionHandler(UserNotAccountException.class)
    public ResponseEntity<String> handleNotUserDataException(UserNotAccountException exception) {
        log.error("[{}] {}", "입력된 이메일과 일치하는 계정이 없습니다" ,ExceptionUtils.getStackTrace(exception));
        return RESPONSE_USER_ACCOUNT_BAD_REQUEST;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getBindingResult().getFieldErrors());
        log.error("[{}] {}", errorMessage.getErrorMessage(), ExceptionUtils.getStackTrace(exception));
        return new ResponseEntity<>(errorMessage.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotLoginException.class)
    public ResponseEntity<String> handleUserNotLoginException(UserNotLoginException exception) {
        log.error("[{}] {}", "유저에 대한 정보를 찾지 못했습니다. 로그인을 해주세요" ,ExceptionUtils.getStackTrace(exception));
        return RESPONSE_USER_UNAUTHORIZED;
    }

    @ExceptionHandler(AwsS3FileNotUploadException.class)
    public ResponseEntity<String> handleAwsS3FileNotUploadException(AwsS3FileNotUploadException exception) {
        log.error("[{}] {}", "s3 파일 업로드가 실패 했습니다", ExceptionUtils.getStackTrace(exception));
        return RESPONSE_AWS_S3_FILE_NOT_UPLOAD;
    }

}