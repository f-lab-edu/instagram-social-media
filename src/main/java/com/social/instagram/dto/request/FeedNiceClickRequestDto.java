package com.social.instagram.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedNiceClickRequestDto extends FeedNiceBatchRequestDto {

    private String niceClickUserId;

    public FeedNiceClickRequestDto(long postId) {
        super(postId);
    }

    public static Map<Long, Long> increaseFeedNiceCount(List<FeedNiceClickRequestDto> feedNiceClickRequestDto) {
        return feedNiceClickRequestDto.stream()
                .collect(Collectors.groupingBy(
                        FeedNiceBatchRequestDto::getPostId, Collectors.counting()
                ));
    }

}