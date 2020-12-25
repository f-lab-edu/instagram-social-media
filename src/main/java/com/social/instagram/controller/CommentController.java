package com.social.instagram.controller;

import com.social.instagram.annotation.LoginValidation;
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

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @LoginValidation
    @ResponseStatus(HttpStatus.CREATED)
    public void comment(@Valid @RequestBody CommentRequestDto commentRequestDto) {
        commentService.comment(commentRequestDto);
    }

}