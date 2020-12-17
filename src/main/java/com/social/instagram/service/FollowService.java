package com.social.instagram.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.social.instagram.domain.Follow;
import com.social.instagram.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final String firebaseToken;
    private final String messageTitle;
    private final String messageBody;

    public FollowService(final FollowRepository followRepository,
                         @Value("${firebase.token}") final String firebaseToken,
                         @Value("${firebase.title}") final String messageTitle,
                         @Value("${firebase.body}") final String messageBody) {
        this.followRepository = followRepository;
        this.firebaseToken = firebaseToken;
        this.messageTitle = messageTitle;
        this.messageBody = messageBody;
    }

    public void follow(Follow follow) {
        followRepository.save(follow);

        FirebaseMessaging.getInstance().sendAsync(followMessage(follow));
    }

    public void cancelFollow(Follow follow) {
        followRepository.deleteByUserIdAndFollowId(follow.getUserId(), follow.getFollowId());
    }

    private Message followMessage(Follow follow) {
        return Message.builder()
                .setToken(firebaseToken)
                .setNotification(Notification.builder()
                        .setTitle(messageTitle)
                        .setBody(follow.getUserId() + messageBody)
                        .build())
                .build();
    }

}