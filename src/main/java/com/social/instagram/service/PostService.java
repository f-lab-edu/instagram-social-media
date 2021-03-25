package com.social.instagram.service;

import com.social.instagram.domain.Post;
import com.social.instagram.dto.PostDto;
import com.social.instagram.dto.request.FeedNiceClickRequestDto;
import com.social.instagram.dto.response.PostResponseDto;
import com.social.instagram.repository.PostRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

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
    private final FeedNiceClickAndNiceBatchService feedNiceClickAndNiceBatchService;

    public PostService(final PostRepository postRepository, final LoginService loginService,
                       final KafkaTemplate<String, FeedNiceClickRequestDto> feedNiceKafkaTemplate,
                       @Value("${kafka.topic.type.nice}") final String niceTopic,
                       final FeedNiceClickAndNiceBatchService feedNiceClickAndNiceBatchService) {
        this.postRepository = postRepository;
        this.loginService = loginService;
        this.feedNiceKafkaTemplate = feedNiceKafkaTemplate;
        this.niceTopic = niceTopic;
        this.feedNiceClickAndNiceBatchService = feedNiceClickAndNiceBatchService;
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

    public void updateFeedNice(FeedNiceClickRequestDto feedNiceClickRequestDto) {
        feedNiceKafkaTemplate.send(niceTopic, feedNiceClickRequestDto);
    }

    @KafkaListener(topics = "${kafka.topic.type.nice}", groupId = "${kafka.topic.type.nice}",
            containerFactory = "feedNiceListenerContainerFactory")
    public void receiveFeedNiceMessage(List<FeedNiceClickRequestDto> feedNiceMessage) {
        feedNiceClickAndNiceBatchService.batchFeedNice(feedNiceMessage);
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

}