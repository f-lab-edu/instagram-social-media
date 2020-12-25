package com.social.instagram.controller;

import com.social.instagram.dto.request.CommentRequestDto;
import com.social.instagram.service.CommentService;
import com.social.instagram.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final SessionService sessionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void comment(@RequestBody CommentRequestDto commentRequestDto) {
        CommentRequestDto.validate(commentRequestDto, sessionService.getUserId());

        commentService.comment(commentRequestDto);
    }

}