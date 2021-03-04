package com.social.instagram.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedNiceBatchRequestDto {

    private long postId;

    public FeedNiceBatchRequestDto(long postId) {
        this.postId = postId;
    }

}