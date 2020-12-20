package com.social.instagram.service.firebase;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.social.instagram.domain.Follow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class WebPushMessageService implements FirebaseService {

    private final String firebaseToken;
    private final String messageTitle;
    private final String messageBody;

    public WebPushMessageService(@Value("${firebase.token}") final String firebaseToken,
                                 @Value("${firebase.title}") final String messageTitle,
                                 @Value("${firebase.body}") final String messageBody) {
        this.firebaseToken = firebaseToken;
        this.messageTitle = messageTitle;
        this.messageBody = messageBody;
    }

    @Override
    public Future<String> sendAsyncMessage(Follow follow) {
        return FirebaseMessaging.getInstance().sendAsync(from(follow));
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