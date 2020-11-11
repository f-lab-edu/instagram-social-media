package com.social.instagram.service;

import com.social.instagram.domain.Post;
import com.social.instagram.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void writePost(Post post) {
        postRepository.save(post);
    }

    public int getId(String userId) {
        return postRepository.findByPostId(userId);
    }

    public void updateComment(long postId, String comment) {
        postRepository.updateComment(postId, comment);
    }

    public List<Post> getPost(String userId) {
        return postRepository.findByUserIdAndFilePathIsNotNull(userId);
    }

}