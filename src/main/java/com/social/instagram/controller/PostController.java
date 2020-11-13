package com.social.instagram.controller;

import com.social.instagram.annotation.LoginValidation;
import com.social.instagram.dto.PostDto;
import com.social.instagram.dto.response.PostResponseDto;
import com.social.instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static com.social.instagram.util.httpstatus.ResponseConstants.RESPONSE_ENTITY_CREATE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    @LoginValidation
    public ResponseEntity<Void> updateComment(@RequestBody PostDto postDto) {
        postService.updateComment(postDto);

        return RESPONSE_ENTITY_CREATE;
    }

    @GetMapping("/{userId}/images")
    public ResponseEntity<List<PostResponseDto>> getPost(@PathVariable String userId) {
        List<PostResponseDto> posts = postService.getPost(userId);

        return ResponseEntity.ok(posts);
    }

}