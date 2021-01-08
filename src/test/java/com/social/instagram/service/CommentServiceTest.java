package com.social.instagram.service;

import com.social.instagram.domain.Comment;
import com.social.instagram.dto.request.CommentRequestDto;
import com.social.instagram.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private LoginService loginService;

    @Mock
    private Comment comment;

    @InjectMocks
    private CommentService commentService;

    private CommentRequestDto commentRequestDto;

    private static final long POST_ID = 84L;
    private static final String COMMENT = "댓글 작성";
    private static final String USER_ID = "testId";

    @BeforeEach
    public void setUp() {
        this.commentRequestDto = CommentRequestDto.builder()
                .postId(POST_ID)
                .comment(COMMENT)
                .build();
    }

    @Test
    @DisplayName("로그인된 유저 ID로 작성한 댓글을 저장소에 저장한다")
    public void saveDatabaseWhenLoggedInUserIdCommentWrite() {
        given(loginService.getUserId()).willReturn(USER_ID);
        given(commentRepository.save(any())).willReturn(comment);

        commentService.comment(commentRequestDto);

        verify(loginService).getUserId();
        verify(commentRepository).save(any());
    }

}