package com.social.instagram.service;

import com.social.instagram.domain.Follow;
import com.social.instagram.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    public void sendFollow(Follow follow) {
        followRepository.save(follow);
    }

}