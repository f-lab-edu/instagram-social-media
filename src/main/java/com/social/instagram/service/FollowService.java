package com.social.instagram.service;

import com.social.instagram.domain.Follow;
import com.social.instagram.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final PushMessageService firebaseService;

    public void follow(Follow follow) {
        followRepository.save(follow);
        firebaseService.sendAsyncMessage(follow);
    }

    public void cancelFollow(Follow follow) {
        followRepository.deleteByUserIdAndFollowId(follow.getUserId(), follow.getFollowId());
    }

}