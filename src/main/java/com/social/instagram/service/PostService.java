package com.social.instagram.service;

import com.social.instagram.domain.Post;
import com.social.instagram.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void writePost(Post post) {
        postRepository.save(post);
    }

}