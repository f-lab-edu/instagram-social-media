package com.social.instagram.service.firebase;

import com.google.firebase.messaging.FirebaseMessaging;
import com.social.instagram.domain.Follow;
import com.social.instagram.factory.PushMessageFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class WebPushService implements PushService {

    private final PushMessageFactory pushMessageFactory;

    @Override
    public Future<String> sendAsyncMessage(Follow follow) {
        return FirebaseMessaging.getInstance().sendAsync(pushMessageFactory.from(follow));
    }

}