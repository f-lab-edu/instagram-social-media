package com.social.instagram.factory;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.social.instagram.domain.Follow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PushMessageFactory {

    private final String firebaseToken;
    private final String messageTitle;
    private final String messageBody;

    public PushMessageFactory(@Value("${firebase.token}") final String firebaseToken,
                              @Value("${firebase.title}") final String messageTitle,
                              @Value("${firebase.body}") final String messageBody) {
        this.firebaseToken = firebaseToken;
        this.messageTitle = messageTitle;
        this.messageBody = messageBody;
    }

    public Message from(Follow follow) {
        return Message.builder()
                .setToken(firebaseToken)
                .setNotification(Notification.builder()
                        .setTitle(messageTitle)
                        .setBody(follow.getUserId() + messageBody)
                        .build())
                .build();
    }

}