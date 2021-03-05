package com.social.instagram.service;

import com.social.instagram.dto.request.FeedNiceClickRequestDto;
import com.social.instagram.dto.request.FeedNiceRequestDto;
import com.social.instagram.util.query.FeedNiceQueries;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedBatchService {

    private final JdbcBatchService jdbcBatchService;

    @Transactional
    public void batchFeedNice(List<FeedNiceClickRequestDto> feedNiceMessage) {
        jdbcBatchService.batchInsert(FeedNiceQueries.POST_NICE_CLICK_QUERY, feedNiceMessage);
        jdbcBatchService.batchInsert(FeedNiceQueries.POST_NICE_QUERY,
                FeedNiceRequestDto.from(FeedNiceClickRequestDto.increaseFeedNiceCount(feedNiceMessage)));
    }

}