package com.social.instagram.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.social.instagram.domain.Follow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

    private final String firebaseToken;
    private final String messageTitle;
    private final String messageBody;

    public FirebaseService(@Value("${firebase.token}") final String firebaseToken,
                           @Value("${firebase.title}") final String messageTitle,
                           @Value("${firebase.body}") final String messageBody) {
        this.firebaseToken = firebaseToken;
        this.messageTitle = messageTitle;
        this.messageBody = messageBody;
    }

    public void sendAsyncMessage(Follow follow) {
        FirebaseMessaging.getInstance().sendAsync(from(follow));
    }

    private Message from(Follow follow) {
        return Message.builder()
                .setToken(firebaseToken)
                .setNotification(Notification.builder()
                        .setTitle(messageTitle)
                        .setBody(follow.getUserId() + messageBody)
                        .build())
                .build();
    }

}