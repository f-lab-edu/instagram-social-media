package com.social.instagram.exception;

public class DuplicateUserIdException extends IllegalArgumentException {

    public DuplicateUserIdException(String message) {
        super(message);
    }

}