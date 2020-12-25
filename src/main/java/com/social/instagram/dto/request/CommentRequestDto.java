package com.social.instagram.dto.request;

import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
public class CommentRequestDto {

    @Positive(message = "postId의 값이 음수입니다")
    private long postId;

    private String comment;

}