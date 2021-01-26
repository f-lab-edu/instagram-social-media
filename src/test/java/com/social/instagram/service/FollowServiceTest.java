package com.social.instagram.service;

import com.social.instagram.domain.Follow;
import com.social.instagram.repository.FollowRepository;
import com.social.instagram.service.firebase.PushService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FollowServiceTest {

    @Mock
    private FollowRepository followRepository;

    @Mock
    private PushService firebaseService;

    @InjectMocks
    private FollowService followService;

    private Follow follow;
    private static final String USER_ID = "taehoon";
    private static final String FOLLOW_ID = "johyun";
    private static final String MESSAGE = "taehoon님이 회원님을 팔로우 했습니다";

    @BeforeEach
    public void setUp() {
        follow = Follow.builder()
                .userId(USER_ID)
                .followId(FOLLOW_ID)
                .build();
    }

    @Test
    @DisplayName("유저를 팔로우 한다")
    public void followUser() {
        given(followRepository.save(any())).willReturn(follow);
        given(firebaseService.sendAsyncMessage(any()))
                .willReturn(CompletableFuture.completedFuture(MESSAGE));

        followService.follow(follow);

        verify(followRepository).save(any());
        verify(firebaseService).sendAsyncMessage(any());
    }

}