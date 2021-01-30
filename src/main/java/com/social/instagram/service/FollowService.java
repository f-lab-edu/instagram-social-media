package com.social.instagram.service;

import com.social.instagram.domain.Follow;
import com.social.instagram.repository.FollowRepository;
import com.social.instagram.service.firebase.PushService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final PushService firebaseService;
    private final LoginService loginService;

    public void follow(String followId) {
        Follow follow = Follow.of(loginService.getUserId(), followId);

        followRepository.save(follow);
        firebaseService.sendAsyncMessage(follow);
    }

    public void cancelFollow(String followId) {
        followRepository.deleteByUserIdAndFollowId(loginService.getUserId(), followId);
    }

}