package com.social.instagram.service.firebase;

import com.social.instagram.domain.Follow;

import java.util.concurrent.Future;

public interface FirebaseService {

    Future<String> sendAsyncMessage(Follow follow);

}