package com.social.instagram.service.firebase;

import com.social.instagram.domain.Follow;

import java.util.concurrent.Future;

public interface PushService {

    Future<String> sendAsyncMessage(Follow follow);

}