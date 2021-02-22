package com.social.instagram.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class FeedNiceRequestDto {

    private long postId;

    private long nice;

    @Builder
    public FeedNiceRequestDto(long postId, long nice) {
        this.postId = postId;
        this.nice = nice;
    }

    public static List<FeedNiceRequestDto> from(Map<Long, Long> feedNice) {
        List<FeedNiceRequestDto> batchFeedNice = new ArrayList<>();

        for (Map.Entry<Long, Long> feedNiceInsert : feedNice.entrySet()) {
            batchFeedNice.add(FeedNiceRequestDto.builder()
                    .postId(feedNiceInsert.getKey())
                    .nice(feedNiceInsert.getValue())
                    .build());
        }

        return batchFeedNice;
    }
}