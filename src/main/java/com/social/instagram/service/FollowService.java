package com.social.instagram.service;

import com.social.instagram.domain.Follow;
import com.social.instagram.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    @Transactional(rollbackFor = Exception.class)
    public void followUserId(String userId, String followerId) {
        followRepository.save(Follow.from(userId, followerId));
    }

}