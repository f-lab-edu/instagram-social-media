package com.social.instagram.controller;

import com.social.instagram.domain.Follow;
import com.social.instagram.dto.request.FollowRequestDto;
import com.social.instagram.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;


import static com.social.instagram.util.httpstatus.ResponseConstants.RESPONSE_ENTITY_CREATE;
import static com.social.instagram.util.httpstatus.ResponseConstants.RESPONSE_ENTITY_OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follows")
public class FollowController {

    private final FollowService followService;

    @PostMapping
    public ResponseEntity<Void> follow(@RequestBody FollowRequestDto followRequestDto) {
        followService.follow(Follow.from(followRequestDto));

        return RESPONSE_ENTITY_CREATE;
    }

    @DeleteMapping
    public ResponseEntity<Void> cancelFollow(@RequestParam String followId) {
        followService.cancelFollow(followId);

        return RESPONSE_ENTITY_OK;
    }

}