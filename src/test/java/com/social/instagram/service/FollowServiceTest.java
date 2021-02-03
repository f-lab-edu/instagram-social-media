package com.social.instagram.service;

import com.social.instagram.domain.Follow;
import com.social.instagram.exception.UserNotLoginException;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FollowServiceTest {

    @Mock
    private FollowRepository followRepository;

    @Mock
    private PushService firebaseService;

    @Mock
    private LoginService loginService;

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

        followService.follow(FOLLOW_ID);

        verify(followRepository).save(any());
        verify(firebaseService).sendAsyncMessage(any());
    }

    @Test
    @DisplayName("유저의 세션이 만료된 상태에서 팔로우 할시 UserNotLoginException을 던진다")
    public void throwUserNotLoginExceptionFollowWhenUserExpire() {
        given(loginService.getUserId()).willThrow(UserNotLoginException.class);

        assertThrows(UserNotLoginException.class, () ->
                followService.follow(FOLLOW_ID));
    }

    @Test
    @DisplayName("팔로우를 취소한다")
    public void cancelFollowId() {
        given(loginService.getUserId()).willReturn(USER_ID);
        doNothing().when(followRepository)
                .deleteByUserIdAndFollowId(anyString(), anyString());

        followService.cancelFollow(FOLLOW_ID);

        verify(loginService).getUserId();
        verify(followRepository).deleteByUserIdAndFollowId(anyString(), anyString());
    }

    @Test
    @DisplayName("유저의 세션이 만료된 상태에 팔로우 삭제시 UserNotLoginException을 던진다")
    public void throwUserNotLoginExceptionFollowCancelWhenUserExpire() {
        given(loginService.getUserId()).willThrow(UserNotLoginException.class);

        assertThrows(UserNotLoginException.class, () ->
                followService.cancelFollow(FOLLOW_ID));
    }

}