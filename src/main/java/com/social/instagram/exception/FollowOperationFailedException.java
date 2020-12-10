package com.social.instagram.exception;

import org.springframework.dao.DataAccessException;

public class FollowOperationFailedException extends DataAccessException {

    public FollowOperationFailedException(String message) {
        super(message);
    }

}