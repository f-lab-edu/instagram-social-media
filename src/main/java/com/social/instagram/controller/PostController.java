package com.social.instagram.controller;

import com.social.instagram.annotation.LoginValidation;
import com.social.instagram.domain.Post;
import com.social.instagram.dto.PostDto;
import com.social.instagram.service.PostService;
import com.social.instagram.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.social.instagram.util.httpstatus.ResponseConstants.RESPONSE_ENTITY_CREATE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final SessionService sessionService;

    @PostMapping
    @LoginValidation
    public ResponseEntity<Void> writePost(@RequestBody PostDto postDto) {
        postService.writePost(Post.changePostUserIdAndComment(postDto, sessionService.getUserId()));

        return RESPONSE_ENTITY_CREATE;
    }

}