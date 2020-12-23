package com.social.instagram.dto.request;

import lombok.Getter;

@Getter
public class CommentRequestDto {

    private long postId;

    private String userId;

    private String comment;

}