package com.social.instagram.dto.request;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class FeedNiceClickRequestDto {

    private long postId;

    private String niceClickUserId;

    private static final long FEED_NICE_COUNT_EMPTY = 0L;

    private static final long FEED_NICE_COUNT_ONE_INCREASE = 1;

    public static Map<Long, Long> FeedNiceCountIncrease(List<FeedNiceClickRequestDto> feedNiceClickRequestDto) {
        Map<Long, Long> feedNiceCount = new HashMap<>();

        for (FeedNiceClickRequestDto feedNice : feedNiceClickRequestDto) {
            long feedNicePostId = feedNice.getPostId();
            feedNiceCount.put(feedNicePostId,
                    feedNiceCount.getOrDefault(feedNicePostId, FEED_NICE_COUNT_EMPTY) + FEED_NICE_COUNT_ONE_INCREASE);
        }

        return feedNiceCount;
    }

}