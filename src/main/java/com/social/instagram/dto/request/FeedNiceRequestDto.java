package com.social.instagram.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedNiceRequestDto extends FeedNiceBatchRequestDto {

    private long nice;

    @Builder
    public FeedNiceRequestDto(long postId, long nice) {
        super(postId);
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