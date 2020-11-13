package com.social.instagram.service;

import com.social.instagram.domain.Post;
import com.social.instagram.dto.PostDto;
import com.social.instagram.dto.response.PostResponseDto;
import com.social.instagram.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final SessionService sessionService;

    public void writePost(Post post) {
        postRepository.save(post);
    }

    public int getId(String userId) {
        return postRepository.findByPostId(userId);
    }

    public void updateComment(PostDto postDto) {
        postRepository.updateComment(getId(sessionService.getUserId()), postDto.getComment());
    }

    public List<PostResponseDto> getPost(String userId, Pageable pageable) {
        return Stream.of(postRepository.findByUserIdAndFilePathIsNotNull(userId, pageable))
                .flatMap(Streamable::stream)
                .map(PostResponseDto::changePostResponseDto)
                .collect(Collectors.toList());
    }

}