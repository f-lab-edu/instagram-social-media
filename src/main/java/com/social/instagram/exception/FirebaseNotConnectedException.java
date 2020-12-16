package com.social.instagram.exception;

public class FirebaseNotConnectedException extends IllegalStateException {

    public FirebaseNotConnectedException(String message) {
        super(message);
    }

}