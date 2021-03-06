package com.social.instagram.controller;

import com.social.instagram.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


import static com.social.instagram.util.httpstatus.ResponseConstants.RESPONSE_ENTITY_CREATE;
import static com.social.instagram.util.httpstatus.ResponseConstants.RESPONSE_ENTITY_OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follows")
public class FollowController {

    private final FollowService followService;

    @PostMapping("/{followId}")
    public ResponseEntity<Void> follow(@PathVariable String followId) {
        followService.follow(followId);

        return RESPONSE_ENTITY_CREATE;
    }

    @DeleteMapping("/{followId}")
    public ResponseEntity<Void> cancelFollow(@PathVariable String followId) {
        followService.cancelFollow(followId);

        return RESPONSE_ENTITY_OK;
    }

}