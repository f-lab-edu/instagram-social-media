package com.social.instagram.service;

import com.social.instagram.dto.request.CommentRequestDto;
import com.social.instagram.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/*
    작성해야 할 테스트 목록
    1. postId가 음수이거나 userId가 없다면 실패하기 -> 전반적인 comment 유효성 검사
    2. 댓글을 작성하면 테스트 성공
*/
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    private CommentRequestDto commentRequestDto;

    @Test
    public void comment_Fail_When_No_PostIdOrUserId() {

    }

}