package com.social.instagram.dto.request;

import com.social.instagram.exception.CommentValidatedFailException;
import lombok.Getter;

import java.util.Optional;

@Getter
public class CommentRequestDto {

    private long postId;

    private String comment;

    public static void validate(CommentRequestDto commentRequestDto, String userId) {
        Optional.ofNullable(userId)
                .orElseThrow(CommentValidatedFailException::new);

        if (commentRequestDto.getPostId() < CommentConstants.POST_MIN) {
            throw new CommentValidatedFailException();
        }

    }

    static class CommentConstants {
        static final int POST_MIN = 0;
    }

}