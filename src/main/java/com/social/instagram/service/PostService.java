package com.social.instagram.service;

import com.social.instagram.domain.Post;
import com.social.instagram.dto.PostDto;
import com.social.instagram.dto.request.FeedNiceClickRequestDto;
import com.social.instagram.dto.request.FeedNiceRequestDto;
import com.social.instagram.dto.response.PostResponseDto;
import com.social.instagram.repository.PostNiceRepository;
import com.social.instagram.repository.PostRepository;
import com.social.instagram.util.query.FeedNiceQueries;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.util.Streamable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    @KafkaListener
    이 어노테이션을 위해 생성된 리스너 컨테이너는 application context의 bean이 아니고
    KafkaListenerEndPointRegistry 유형의 인프라 빈에 등록된다.
    인프라 빈은 auto startup이 true로 설정되어 있을 때 프레임워크에 의해 자동으로 선언되며 컨테이너의 라이프 사이클을 관리한다.
    리스너 컨테이너는 SmartLifeCycle을 구현하며 autoStartup은 기본적으로 true이다.
*/

@Service
public class PostService {

    private final PostRepository postRepository;
    private final LoginService loginService;
    private final KafkaTemplate<String, FeedNiceClickRequestDto> feedNiceKafkaTemplate;
    private final String niceTopic;
    private final JdbcBatchService jdbcBatchService;
    private final RedisTemplate<Long, Long> feedsNiceRedisTemplate;
    private final PostNiceRepository postNiceRepository;

    public PostService(final PostRepository postRepository, final LoginService loginService,
                       final KafkaTemplate<String, FeedNiceClickRequestDto> feedNiceKafkaTemplate,
                       @Value("${kafka.topic.type.nice}") final String niceTopic,
                       final JdbcBatchService jdbcBatchService,
                       final RedisTemplate<Long, Long> feedsNiceRedisTemplate,
                       final PostNiceRepository postNiceRepository) {
        this.postRepository = postRepository;
        this.loginService = loginService;
        this.feedNiceKafkaTemplate = feedNiceKafkaTemplate;
        this.niceTopic = niceTopic;
        this.jdbcBatchService = jdbcBatchService;
        this.feedsNiceRedisTemplate = feedsNiceRedisTemplate;
        this.postNiceRepository = postNiceRepository;
    }

    @CacheEvict(value = "feedsPerUser", key = "#post.userId")
    public void writePost(Post post) {
        postRepository.save(post);
    }

    public int getId(String userId) {
        return postRepository.findByPostId(userId);
    }

    public void updateComment(PostDto postDto) {
        postRepository.updateComment(getId(loginService.getUserId()), postDto.getComment());
    }

    @Cacheable(value = "feedsPerUser", key = "#userId")
    public List<PostResponseDto> getPost(String userId, Pageable pageable) {
        return Stream.of(postRepository.findByUserIdAndFilePathIsNotNull(userId, pageable))
                .flatMap(Streamable::stream)
                .map(PostResponseDto::from)
                .collect(Collectors.toList());
    }

    public void redisFeedNiceCount(List<PostResponseDto> posts) {

    }

    public void updateFeedNice(FeedNiceClickRequestDto feedNiceClickRequestDto) {
        feedNiceKafkaTemplate.send(niceTopic, feedNiceClickRequestDto);
    }

    @KafkaListener(topics = "${kafka.topic.type.nice}", groupId = "${kafka.topic.type.nice}",
            containerFactory = "feedNiceListenerContainerFactory")
    public void receiveFeedNiceMessage(List<FeedNiceClickRequestDto> feedNiceMessage) {
        batchFeedNice(feedNiceMessage);
    }

    @Transactional
    public void batchFeedNice(List<FeedNiceClickRequestDto> feedNiceMessage) {
        jdbcBatchService.batchInsert(FeedNiceQueries.POST_NICE_CLICK_QUERY, feedNiceMessage);
        jdbcBatchService.batchInsert(FeedNiceQueries.POST_NICE_QUERY,
                FeedNiceRequestDto.from(FeedNiceClickRequestDto.increaseFeedNiceCount(feedNiceMessage)));
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

}