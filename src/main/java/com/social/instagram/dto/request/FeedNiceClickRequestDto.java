package com.social.instagram.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedNiceClickRequestDto extends FeedNiceBatchRequestDto {

    private String niceClickUserId;

    private static final long FEED_NICE_COUNT_EMPTY = 0L;

    private static final long FEED_NICE_COUNT_ONE_INCREASE = 1;

    public FeedNiceClickRequestDto(long postId) {
        super(postId);
    }

    public static Map<Long, Long> increaseFeedNiceCount(List<FeedNiceClickRequestDto> feedNiceClickRequestDto) {
        Map<Long, Long> feedNiceCount = new HashMap<>();

        for (FeedNiceClickRequestDto feedNice : feedNiceClickRequestDto) {
            long feedNicePostId = feedNice.getPostId();
            feedNiceCount.put(feedNicePostId,
                    feedNiceCount.getOrDefault(feedNicePostId, FEED_NICE_COUNT_EMPTY) + FEED_NICE_COUNT_ONE_INCREASE);
        }

        return feedNiceCount;
    }

}