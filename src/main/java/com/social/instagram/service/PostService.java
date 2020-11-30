package com.social.instagram.service;

import com.social.instagram.domain.Post;
import com.social.instagram.dto.PostDto;
import com.social.instagram.dto.response.PostResponseDto;
import com.social.instagram.repository.PostNiceRepository;
import com.social.instagram.repository.PostRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final SessionService sessionService;
    private final PostNiceRepository postNiceRepository;
    private final KafkaTemplate<String, Long> kafkaTemplate;
    private final String niceTopic;

    public PostService(final PostRepository postRepository, final SessionService sessionService,
                       final PostNiceRepository postNiceRepository, final KafkaTemplate<String, Long> kafkaTemplate,
                       @Value("${kafka.topic.type.nice}") final String niceTopic) {
        this.postRepository = postRepository;
        this.sessionService = sessionService;
        this.postNiceRepository = postNiceRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.niceTopic = niceTopic;
    }

    public void writePost(Post post) {
        postRepository.save(post);
    }

    public int getId(String userId) {
        return postRepository.findByPostId(userId);
    }

    public void updateComment(PostDto postDto) {
        postRepository.updateComment(getId(sessionService.getUserId()), postDto.getComment());
    }

    @Cacheable(value = "feedsPerUser", key = "#userId")
    public List<PostResponseDto> getPost(String userId, Pageable pageable) {
        return Stream.of(postRepository.findByUserIdAndFilePathIsNotNull(userId, pageable))
                .flatMap(Streamable::stream)
                .map(PostResponseDto::from)
                .collect(Collectors.toList());
    }

    public void updateNice(long id) {
        kafkaTemplate.send(niceTopic, id);
    }

    @KafkaListener(topics = "nice", groupId = "nice",
            containerFactory = "ListenerContainerFactory")
    public void receiveNiceMessage(List<Long> niceMessage) {
        for (long niceId : niceMessage) {
            postNiceRepository.updateNice(niceId);
        }

    }

}