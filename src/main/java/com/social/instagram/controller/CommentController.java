package com.social.instagram.controller;

import com.social.instagram.dto.request.CommentRequestDto;
import com.social.instagram.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.social.instagram.util.httpstatus.ResponseConstants.RESPONSE_ENTITY_CREATE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> comment(@RequestBody CommentRequestDto commentRequestDto) {
        commentService.comment(commentRequestDto);

        return RESPONSE_ENTITY_CREATE;
    }

}