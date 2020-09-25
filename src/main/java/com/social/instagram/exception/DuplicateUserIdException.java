package com.social.instagram.exception;

public class DuplicateUserIdException extends RuntimeException {

    public DuplicateUserIdException(String message) {
        super(message);
    }

}