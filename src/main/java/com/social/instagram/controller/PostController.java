package com.social.instagram.controller;

import com.social.instagram.annotation.LoginValidation;
import com.social.instagram.dto.PostDto;
import com.social.instagram.dto.request.FeedNiceClickRequestDto;
import com.social.instagram.dto.response.PostResponseDto;
import com.social.instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

import static com.social.instagram.util.httpstatus.ResponseConstants.RESPONSE_ENTITY_CREATE;
import static com.social.instagram.util.httpstatus.ResponseConstants.RESPONSE_ENTITY_OK;

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

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getPost(@RequestParam String userId,
                @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        List<PostResponseDto> posts = postService.getPost(userId, pageable);

        return ResponseEntity.ok(posts);
    }

    @PutMapping
    public ResponseEntity<Void> updateNice(@RequestBody FeedNiceClickRequestDto feedNiceClickRequestDto) {
        postService.updateFeedNice(feedNiceClickRequestDto);

        return RESPONSE_ENTITY_OK;
    }

    @DeleteMapping
    public void deletePost(@RequestParam long id) {
        postService.deletePost(id);
    }

}