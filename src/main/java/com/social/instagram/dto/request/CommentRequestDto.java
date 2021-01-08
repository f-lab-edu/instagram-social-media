package com.social.instagram.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
public class CommentRequestDto {

    @Positive(message = "postId의 값이 음수입니다")
    private long postId;

    private String comment;

    @Builder
    public CommentRequestDto(long postId, String comment) {
        this.postId = postId;
        this.comment = comment;
    }

}